package com.sguru.lipidok.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ScaffoldBottomBarLipidOk(
    textButton: String,
    enableButton: Boolean = true,
    onButtonClick: () -> Unit
) {
    Button(
        textButton = textButton,
        enableButton = enableButton,
        onButtonClick = onButtonClick,
    )
}

@Composable
private fun Button(
    textButton: String,
    enableButton: Boolean,
    onButtonClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
    ) {
        ButtonLipidOk(onClick = onButtonClick, text = textButton, enable = enableButton)
    }
}
