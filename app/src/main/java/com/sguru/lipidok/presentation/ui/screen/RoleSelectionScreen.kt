package com.sguru.lipidok.presentation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.component.ButtonLipidOk
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk

@Composable
internal fun RoleSelectionScreen(
    onButton1Click: () -> Unit,
    onButton2Click: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarLipidOk(
                stringResTitle = R.string.HeadersRoleSelectionScreen,
            )
        },
        content = { paddingValue ->
            RoleSelectionContent(
                paddingValue = paddingValue,
                onButton1Click = onButton1Click,
                onButton2Click = onButton2Click,
            )
        }
    )
}

@Composable
private fun RoleSelectionContent(
    paddingValue: PaddingValues,
    onButton1Click: () -> Unit,
    onButton2Click: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text20LipidOk(text = "Выбери свою роль")

        Spacer(modifier = Modifier.height(120.dp))

        ButtonLipidOk(onClick = onButton1Click, text = "Врач")

        Spacer(modifier = Modifier.height(32.dp))

        ButtonLipidOk(onClick = onButton2Click, text = "Пациент")
    }
}