package com.sguru.lipidok.presentation.ui.screen.MainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.domain.model.LipidProfileModel
import com.sguru.lipidok.presentation.ui.component.ButtonLipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.model.ScreenEvent
import com.sguru.lipidok.presentation.ui.viewmodel.MainViewModel

const val PATIENT_NAV = 0
const val DATA_BASE_NAV = 1
const val GENERAL_NAV = 2

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun MainScreen(
    isNavigationIconClick: () -> Unit,
    onButtonLipidProfileAssessmentClick: () -> Unit,
    onButtonIndividualSelectionTherapyClick: () -> Unit,
    onClickCreatePatientButton: () -> Unit,
    selectedItemNavigationBar: Int,
    onClickNavBar: (Int) -> Unit,
    viewModel: MainViewModel,
    onEvent: (ScreenEvent) -> Unit,
    onPatientClick:  () -> Unit,
) {
    var listPatient by remember { mutableStateOf(viewModel.patients.value) }
    listPatient = viewModel.patients.collectAsState().value.asReversed()
    Screen(
        isNavigationIconClick = isNavigationIconClick,
        onButtonLipidProfileAssessmentClick = onButtonLipidProfileAssessmentClick,
        onButtonIndividualSelectionTherapyClick = onButtonIndividualSelectionTherapyClick,
        selectedItemNavigationBar = selectedItemNavigationBar,
        onClickNavBar = onClickNavBar,
        onClickCreatePatientButton = onClickCreatePatientButton,
        listPatient = listPatient,
        onEvent = onEvent,
        onPatientClick = onPatientClick,
    )
}

@Composable
private fun Screen(
    isNavigationIconClick: () -> Unit,
    onButtonLipidProfileAssessmentClick: () -> Unit,
    onButtonIndividualSelectionTherapyClick: () -> Unit,
    selectedItemNavigationBar: Int,
    onClickNavBar: (Int) -> Unit,
    onClickCreatePatientButton: () -> Unit,
    listPatient: List<com.sguru.lipidok.domain.model.PatientModel>,
    onEvent: (ScreenEvent) -> Unit,
    onPatientClick: () -> Unit
) {


    Scaffold(
        topBar = {
            TopBarLipidOk(
                isNavigationIconClick = isNavigationIconClick,
                stringResTitle = when (selectedItemNavigationBar) {
                    DATA_BASE_NAV -> {
                        R.string.DataBaseScreen
                    }

                    GENERAL_NAV -> {
                        R.string.GeneralScreen
                    }

                    else -> {
                        null
                    }
                }

            )
        },
        content = { paddingValue ->
            when (selectedItemNavigationBar) {
                PATIENT_NAV -> {
                    MainContent(
                        paddingValue = paddingValue,
                        onButtonLipidProfileAssessmentClick = onButtonLipidProfileAssessmentClick,
                        onButtonIndividualSelectionTherapyClick = onButtonIndividualSelectionTherapyClick,
                    )
                }

                DATA_BASE_NAV -> {
                    DataBaseContent(
                        paddingValue = paddingValue,
                        listPatient = listPatient,
                        onDeleteClick = {
                            onEvent(ScreenEvent.DataBase.OnButtonClickedDeletePatient(it))
                        },
                        onPatientClick = {
                            onEvent(ScreenEvent.DataBase.OnItemPatientClicked(it))
                            onPatientClick.invoke()
                        }
                    )

                }

                GENERAL_NAV -> {
//                    if (patientInfo != null) {
//
//                        GeneralContent(
//                            paddingValue = paddingValue,
//                            patientInfo = patientInfo,
//                        )
//                    }
                }
            }
        },
        bottomBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (selectedItemNavigationBar == DATA_BASE_NAV) {
                    Spacer(modifier = Modifier.height(16.dp))

                    ButtonLipidOk(
                        onClick = onClickCreatePatientButton,
                        text = "Создать пациента",
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                NavigationBarSample(
                    selectedItemNavigationBar,
                    onClickNavBar = onClickNavBar,
                )
            }
        }
    )
}

@Composable
private fun MainContent(
    paddingValue: PaddingValues,
    onButtonLipidProfileAssessmentClick: () -> Unit,
    onButtonIndividualSelectionTherapyClick: () -> Unit,
) {
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
                Image(
                    modifier = Modifier.size(300.dp, 180.dp),
                    painter = painterResource(id = R.drawable.image_logo),
                    contentDescription = null
                )
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
                ButtonLipidOk(
                    onClick = onButtonLipidProfileAssessmentClick,
                    text = "Оценка липидного профиля"
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                ButtonLipidOk(
                    onClick = onButtonIndividualSelectionTherapyClick,
                    text = "Индивидуальный подбор терапии"
                )
            }
        }


    }
}

@Composable
fun NavigationBarSample(
    selectedItemNavigationBar: Int,
    onClickNavBar: (Int) -> Unit,
) {

    val items = listOf("Пациент", "База данных", "Общее")

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Star, contentDescription = item) },
                label = { Text(item) },
                selected = selectedItemNavigationBar == index,
                onClick = { onClickNavBar(index) }
            )
        }
    }
}
