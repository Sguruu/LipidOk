package com.sguru.lipidok.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.domain.model.PatientModel
import com.sguru.lipidok.presentation.ui.component.ButtonLipidOk
import com.sguru.lipidok.presentation.ui.component.ExposedDropdownMenuLipidOkV2
import com.sguru.lipidok.presentation.ui.component.OutlinedTextFieldDigitLipidOk
import com.sguru.lipidok.presentation.ui.component.OutlinedTextFieldLipidOk
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.model.LipidRiskGroupType
import com.sguru.lipidok.presentation.ui.model.ScreenEvent

@Composable
internal fun EditPatientScreen(
    isNavigationIconClick: () -> Unit,
    patientModel: PatientModel,
    isOnSaveButtonClick: () -> Unit,
    onEvent: (ScreenEvent) -> Unit,
) {
    var actualPatientModel by remember { mutableStateOf(patientModel) }
    Screen(
        patientModel = actualPatientModel,
        updatePatientModel = {
            actualPatientModel = it
        },
        isNavigationIconClick = isNavigationIconClick,
        isOnSaveButtonClick = {
            onEvent(ScreenEvent.EditPatient.OnButtonClickEditPatient(actualPatientModel))
            isOnSaveButtonClick.invoke()
        },
    )
}

@Composable
private fun Screen(
    patientModel: PatientModel,
    updatePatientModel: (PatientModel) -> Unit,
    isNavigationIconClick: () -> Unit,
    isOnSaveButtonClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarLipidOk(
                stringResTitle = R.string.EditPatientScreen,
                isNavigationIconClick = isNavigationIconClick
            )
        },
        content = { paddingValue ->
            Content(
                paddingValue = paddingValue,
                patientModel = patientModel,
                updatePatientModel = updatePatientModel,
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                ButtonLipidOk(
                    onClick = isOnSaveButtonClick,
                    text = "Сохранить"
                )
            }
        },
    )

}

@Composable
private fun Content(
    paddingValue: PaddingValues,
    patientModel: PatientModel,
    updatePatientModel: (PatientModel) -> Unit,
) {
    var selectedOptionText by remember {
        mutableStateOf(
            LipidRiskGroupType.getByTextType(
                patientModel.riskLevel
            ).text
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldLipidOk(
                    defaultValue = patientModel.name,
                    labelText = "Имя",
                    onValueChange = {
                        updatePatientModel(patientModel.copy(name = it))
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldLipidOk(
                    defaultValue = patientModel.surname,
                    labelText = "Фамилия",
                    onValueChange = {
                        updatePatientModel(patientModel.copy(surname = it))
                    })
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldDigitLipidOk(
                    defaultValue = patientModel.emias,
                    labelText = "Номер пациента ЕМИАС",
                    onValueChange = {
                        updatePatientModel(patientModel.copy(emias = it))
                    })
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SimpleGrayLine()
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text20LipidOk(
                    text = "Уровень риска развития гиперлипидемии",
                    textAlign = TextAlign.Center
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                ExposedDropdownMenuLipidOkV2(
                    getSelectedOption = { valueString, index ->
                        selectedOptionText = valueString
                        val lipidRiskGroupType =
                            LipidRiskGroupType.getByTextType(selectedOptionText)
                        updatePatientModel(patientModel.copy(riskLevel = lipidRiskGroupType.text))
                    },
                    listOptions = LipidRiskGroupType.entries.map { it.text },
                    selectedOptionText = selectedOptionText
                )
            }
        }
    }
}