package com.sguru.lipidok.presentation.ui.screen.MainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.domain.model.LipidProfileModel
import com.sguru.lipidok.domain.model.PatientModel
import com.sguru.lipidok.presentation.ui.component.OutlinedTextFieldOutputLipidOk

@Composable
internal fun GeneralContent(
    paddingValue: PaddingValues,
    patientInfo: Pair<PatientModel, List<LipidProfileModel>>

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldOutputLipidOk(
                    labelText = "Имя",
                    text = patientInfo.first.name
                )
            }
        }
    }
}