package com.sguru.lipidok.presentation.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
internal fun Text20LipidOk(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified,
    bold: Boolean = false,
) {
    Text(
        modifier = modifier,
        textAlign = textAlign,
        text = text,
        fontSize = 20.sp,
        color = color,
        fontWeight = if (bold) {
            FontWeight.Bold
        } else null
    )
}

@Composable
internal fun Text16LipidOk(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified,
    bold: Boolean = false,
) {
    Text(
        modifier = modifier,
        textAlign = textAlign,
        text = text,
        fontSize = 16.sp,
        color = color,
        fontWeight = if (bold) {
            FontWeight.Bold
        } else null
    )
}

@Composable
internal fun Text14LipidOk(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        textAlign = textAlign,
        text = text,
        fontSize = 14.sp,
    )
}