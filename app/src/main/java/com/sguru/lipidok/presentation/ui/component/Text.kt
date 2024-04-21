package com.sguru.lipidok.presentation.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
internal fun Text20LipidOk(text: String, textAlign: TextAlign? = null) {
    Text(
        textAlign = textAlign,
        text = text,
        fontSize = 20.sp,
    )
}