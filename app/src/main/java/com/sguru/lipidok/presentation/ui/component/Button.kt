package com.sguru.lipidok.presentation.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ButtonLipidOk(onClick: () -> Unit, text: String) {
    Button(
        modifier = Modifier
            .width(323.dp)
            .sizeIn(minHeight = 55.dp),
        onClick = onClick,
        content = {
            Text20LipidOk(text = text, textAlign = TextAlign.Center)
        },
    )
}