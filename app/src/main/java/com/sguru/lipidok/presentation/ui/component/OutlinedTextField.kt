package com.sguru.lipidok.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
internal fun OutlinedTextFieldLipidOk(
    labelText: String
) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = {
            if (it.text.all { char -> char.isDigit() }) {
                val number = it.text.toIntOrNull()
                if (number != null && number <= 200) {
                    text = it
                } else if (
                    number == null
                ) {
                    text = it
                }
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
        ),
        label = { Text16LipidOk(text = labelText) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            unfocusedLabelColor = Color.Gray,
            focusedLabelColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Black,
            focusedPlaceholderColor = Color.Black,

            )
    )
}