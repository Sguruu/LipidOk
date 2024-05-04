package com.sguru.lipidok.presentation.ui.screen.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sguru.lipidok.presentation.ui.component.Text14LipidOk
import com.sguru.lipidok.presentation.ui.theme.Wave2

@Composable
internal fun DataBaseContent(
    paddingValue: PaddingValues,
    listPatient: List<com.sguru.lipidok.domain.model.PatientModel>,
    onDeleteClick: (Long) -> Unit,
    onPatientClick: (Long) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    var indexDeletePatient by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
            if (listPatient.isNotEmpty()) {
                listPatient.forEachIndexed { index, patientModel ->
                    item {
                        ContactListItem(
                            lastName = patientModel.surname,
                            firstName = patientModel.name,
                            emias = patientModel.emias,
                            onDeleteClick = {
                                indexDeletePatient = index
                                showDialog = true
                            },
                            onPatientClick = {
                                onPatientClick(listPatient[index].id)
                            }
                        )
                    }
                }
            } else {
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text14LipidOk(text = "Данных нет")
                }

            }
        }
    }

    AlertDialogSample(
        openDialog = showDialog,
        confirmButton = {
            showDialog = false
            indexDeletePatient?.let { index ->
                onDeleteClick(listPatient[index].id)
                indexDeletePatient = null
            }
        },
        dismissButton = {
            showDialog = false
        }
    )
}

@Composable
fun ContactListItem(
    lastName: String,
    firstName: String,
    emias: String,
    onDeleteClick: () -> Unit,
    onPatientClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Wave2)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { onPatientClick.invoke() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Contact Icon",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp)
                )
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = lastName, fontWeight = FontWeight.Bold)
                        Text(text = firstName, modifier = Modifier.padding(start = 4.dp))
                    }
                    Text(text = "ЕМИАС : $emias")
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .clickable {
                        onDeleteClick.invoke()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear Icon",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp)
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Composable
fun AlertDialogSample(
    openDialog: Boolean,
    confirmButton: () -> Unit,
    dismissButton: () -> Unit,
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onCloseRequest.
                dismissButton.invoke()
            },
            title = {
                Text(text = "Вы уверены, что хотите удалить запись?")
            },
            text = {
                Text("")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        confirmButton.invoke()
                    }
                ) {
                    Text("Да", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dismissButton.invoke()
                    }
                ) {
                    Text("Нет", color = Color.White)
                }
            }
        )
    }
}