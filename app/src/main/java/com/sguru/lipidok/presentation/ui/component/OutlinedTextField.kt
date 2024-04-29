package com.sguru.lipidok.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
internal fun OutlinedTextFieldAgeLipidOk(
    labelText: String,
    onValueChange: (String) -> Unit = {},
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

@Composable
internal fun OutlinedTextFieldLipidOk(
    labelText: String,
    onValueChange: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
            onValueChange(it.text)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
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

@Composable
internal fun OutlinedTextFieldDigitLipidOk(
    labelText: String,
    onValueChange: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
            onValueChange(it.text)
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

@Composable
internal fun OutlinedTextFieldFloatLipidOk(
    defaultValue: String = "",
    labelText: String,
    onValueChange: (Float) -> Unit = {},
) {
    var value by remember { mutableStateOf(TextFieldValue(text = defaultValue)) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = {
            if (it.text.toFloatOrNull() != null || it.text.isEmpty()) {
                value = TextFieldValue(text = it.text, selection = it.selection)
                onValueChange(it.text.toFloatOrNull() ?: 0F)
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

@Composable
internal fun OutlinedTextFieldOutputLipidOk(
    labelText: String,
    text: String,
    onValueChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        enabled = false,
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = {},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
        ),
        label = { Text14LipidOk(text = labelText) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            unfocusedLabelColor = Color.Gray,
            disabledTextColor = Color.Black,
            disabledLabelColor = Color.Gray,
            focusedLabelColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Transparent,
            focusedPlaceholderColor = Color.Transparent,
        )
    )
}