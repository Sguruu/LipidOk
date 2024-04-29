package com.sguru.lipidok.presentation.ui.component

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun ButtonLipidOk(
    onClick: () -> Unit,
    enable: Boolean = true,
    text: String
) {
    Button(
        modifier = Modifier
            .width(323.dp)
            .sizeIn(minHeight = 55.dp),
        onClick = onClick,
        enabled = enable,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = Color.Gray,
        ),
        content = {
            Text20LipidOk(text = text, textAlign = TextAlign.Center)
        },
    )
}