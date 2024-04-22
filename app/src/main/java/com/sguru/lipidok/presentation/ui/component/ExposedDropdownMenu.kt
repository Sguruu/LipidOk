package com.sguru.lipidok.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sguru.lipidok.presentation.ui.theme.Grey
import com.sguru.lipidok.presentation.ui.theme.Wave
import com.sguru.lipidok.presentation.ui.theme.Wave2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExposedDropdownMenuLipidOk(
    textLabel: String,
    listOptions: List<String> = listOf(
        "Option 1",
        "Option 2",
        "Option 3",
        "Option 4",
        "Option 5"
    ),
    getSelectedOption: (String) -> Unit
) {
    val options = listOf("Не выбрано") + listOptions
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text14LipidOk(text = textLabel) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.White,
                unfocusedPlaceholderColor = Color.Black,
                focusedPlaceholderColor = Color.Black,
                focusedContainerColor = Wave,
                unfocusedContainerColor = Color.White,
                unfocusedTrailingIconColor = Color.Black,
                focusedTrailingIconColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
        )
        ExposedDropdownMenu(
            modifier = Modifier.background(Wave2),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            for (i in 1..options.lastIndex) {
                DropdownMenuItem(
                    text = { Text(options[i]) },
                    onClick = {
                        selectedOptionText = options[i]
                        expanded = false
                        getSelectedOption(options[i])
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExposedDropdownMenuLipidOkV2(
    listOptions: List<String>,
    getSelectedOption: (String, Int) -> Unit,
    selectedOptionText:String,
) {
    Log.d("MyTest", ">>>ExposedDropdownMenuLipidOkV2")
    val options = listOf("Не выбрано") + listOptions
    var expanded by remember { mutableStateOf(false) }
   // var selectedOptionText by remember { mutableStateOf("Не выбрано") }


    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedPlaceholderColor = Color.Black,
                focusedPlaceholderColor = Color.Black,
                focusedContainerColor = Grey,
                unfocusedContainerColor = Color.Transparent,
                unfocusedTrailingIconColor = Color.Black,
                focusedTrailingIconColor = Color.Black,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
        )
        ExposedDropdownMenu(
            modifier = Modifier.background(Wave2),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            for (i in 1..options.lastIndex) {
                DropdownMenuItem(
                    text = { Text16LipidOk(text = options[i]) },
                    onClick = {
//                        selectedOptionText = options[i]
                        expanded = false
                        getSelectedOption(options[i], i)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}