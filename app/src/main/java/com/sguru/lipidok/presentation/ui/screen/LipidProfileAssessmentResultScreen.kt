package com.sguru.lipidok.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.component.ExposedDropdownMenuLipidOk
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.model.LipidProfileQuestions
import com.sguru.lipidok.presentation.ui.model.LipidProfileResult

@Composable
internal fun LipidProfileAssessmentResultScreen(
    isNavigationIconClick: () -> Unit,
    lipidProfileResult: LipidProfileResult,
) {
    Scaffold(
        topBar = {
            TopBarLipidOk(
                stringResTitle = R.string.LipidProfileAssessmentScreen,
                isNavigationIconClick = isNavigationIconClick
            )
        },
        content = { paddingValue ->
            LipidProfileAssessmentResulContent(
                paddingValue = paddingValue,
                lipidProfileResult = lipidProfileResult
            )
        },
    )
}

@Composable
private fun LipidProfileAssessmentResulContent(
    paddingValue: PaddingValues,
    lipidProfileResult: LipidProfileResult,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text20LipidOk(text = lipidProfileResult.resultTitle)
        Spacer(modifier = Modifier.height(32.dp))
        Text20LipidOk(text = lipidProfileResult.description)
    }
}