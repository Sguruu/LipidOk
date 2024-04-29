package com.sguru.lipidok.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.component.ButtonLipidOk
import com.sguru.lipidok.presentation.ui.component.ExposedDropdownMenuLipidOkV2
import com.sguru.lipidok.presentation.ui.component.OutlinedTextFieldDigitLipidOk
import com.sguru.lipidok.presentation.ui.component.OutlinedTextFieldLipidOk
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.theme.LipidOkTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sguru.lipidok.presentation.ui.component.OutlinedTextFieldFloatLipidOk
import com.sguru.lipidok.presentation.ui.model.LipidProfile
import com.sguru.lipidok.presentation.ui.model.LipidRiskGroupType
import com.sguru.lipidok.presentation.ui.model.PatientModel
import com.sguru.lipidok.presentation.ui.model.ScreenEvent

@Composable
internal fun CreatePatientScreen(
    isNavigationIconClick: () -> Unit,
    onEvent: (ScreenEvent) -> Unit,
) {
    var actualPatientModel by remember {
        mutableStateOf<PatientModel>(
            PatientModel(
                name = "",
                surname = "",
                emias = "",
                riskLevel = LipidRiskGroupType.LOW,
                lipidProfile = listOf()
            )
        )
    }

    Screen(
        isNavigationIconClick = isNavigationIconClick,
        onClickSavePatientButton = {
            onEvent(
                ScreenEvent.CreatePatient.OnButtonClickedCreatePatient(
                    actualPatientModel
                )
            )
        },
        actualPatientModel = actualPatientModel,
        updatePatientModel = {
            actualPatientModel = it
        }
    )
}

@Composable
private fun Screen(
    isNavigationIconClick: () -> Unit,
    onClickSavePatientButton: () -> Unit,
    actualPatientModel: PatientModel,
    updatePatientModel: (PatientModel) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarLipidOk(
                stringResTitle = R.string.CreatePatientScreen,
                isNavigationIconClick = isNavigationIconClick
            )
        },
        content = { paddingValue ->
            Content(
                paddingValue = paddingValue,
                actualPatientModel = actualPatientModel,
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
                    onClick = onClickSavePatientButton,
                    text = "Сохранить"
                )
            }
        },
    )
}

@Composable
private fun Content(
    paddingValue: PaddingValues,
    actualPatientModel: PatientModel,
    updatePatientModel: (PatientModel) -> Unit,
) {
    var selectedOptionText by remember { mutableStateOf("Не выбрано") }
    var isAdList by remember { mutableStateOf(true) }

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
                    labelText = "Имя",
                    onValueChange = {
                        updatePatientModel(actualPatientModel.copy(name = it))
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldLipidOk(
                    labelText = "Фамилия",
                    onValueChange = {
                        updatePatientModel(actualPatientModel.copy(surname = it))
                    })
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldDigitLipidOk(
                    labelText = "Номер пациента ЕМИАС",
                    onValueChange = {
                        updatePatientModel(actualPatientModel.copy(emias = it))
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
                        updatePatientModel(actualPatientModel.copy(riskLevel = lipidRiskGroupType))
                    },
                    listOptions = LipidRiskGroupType.entries.map { it.text },
                    selectedOptionText = selectedOptionText
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SimpleGrayLine()
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text20LipidOk(
                    text = "Данные липидного профиля",
                    textAlign = TextAlign.Center
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldFloatLipidOk(
                    labelText = "Общий холестерин",
                    onValueChange = {
                        val newLipidProfile =
                            getNewFirstLipidProfile(actualPatientModel.lipidProfile).copy(
                                cholesterol = it
                            )
                        updatePatientModel(
                            if (!isAdList) {
                                actualPatientModel.copy(
                                    lipidProfile = listOf(newLipidProfile) +
                                            actualPatientModel.lipidProfile.filterIndexed { index, _ ->
                                                index != 0
                                            }
                                )
                            } else {
                                isAdList = false
                                actualPatientModel.copy(
                                    lipidProfile = listOf(newLipidProfile) +
                                            actualPatientModel.lipidProfile
                                )
                            }
                        )
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldFloatLipidOk(
                    labelText = "ЛПНП",
                    onValueChange = {
                        val newLipidProfile =
                            getNewFirstLipidProfile(actualPatientModel.lipidProfile).copy(
                                lpnp = it
                            )
                        updatePatientModel(
                            if (!isAdList) {
                                actualPatientModel.copy(
                                    lipidProfile = listOf(newLipidProfile) +
                                            actualPatientModel.lipidProfile.filterIndexed { index, _ ->
                                                index != 0
                                            }
                                )
                            } else {
                                isAdList = false
                                actualPatientModel.copy(
                                    lipidProfile = listOf(newLipidProfile) +
                                            actualPatientModel.lipidProfile
                                )
                            }
                        )
                    })
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldFloatLipidOk(
                    labelText = "ЛПВП",
                    onValueChange = {
                        val newLipidProfile =
                            getNewFirstLipidProfile(actualPatientModel.lipidProfile).copy(
                                lpvp = it
                            )
                        updatePatientModel(
                            if (!isAdList) {
                                actualPatientModel.copy(
                                    lipidProfile = listOf(newLipidProfile) +
                                            actualPatientModel.lipidProfile.filterIndexed { index, _ ->
                                                index != 0
                                            }
                                )
                            } else {
                                isAdList = false
                                actualPatientModel.copy(
                                    lipidProfile = listOf(newLipidProfile) +
                                            actualPatientModel.lipidProfile
                                )
                            }
                        )
                    })
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldFloatLipidOk(
                    labelText = "Триглицериды",
                    onValueChange = {
                        val newLipidProfile =
                            getNewFirstLipidProfile(actualPatientModel.lipidProfile).copy(
                                triglycerols = it
                            )
                        updatePatientModel(
                            if (!isAdList) {
                                actualPatientModel.copy(
                                    lipidProfile = listOf(newLipidProfile) +
                                            actualPatientModel.lipidProfile.filterIndexed { index, _ ->
                                                index != 0
                                            }
                                )
                            } else {
                                isAdList = false
                                actualPatientModel.copy(
                                    lipidProfile = listOf(newLipidProfile) +
                                            actualPatientModel.lipidProfile
                                )
                            }
                        )
                    })
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldFloatLipidOk(
                    labelText = "Индекс атерогенности",
                    onValueChange = {
                        val newLipidProfile =
                            getNewFirstLipidProfile(actualPatientModel.lipidProfile).copy(
                                atherogenicIndex = it
                            )
                        updatePatientModel(
                            if (!isAdList) {
                                actualPatientModel.copy(
                                    lipidProfile = listOf(newLipidProfile) +
                                            actualPatientModel.lipidProfile.filterIndexed { index, _ ->
                                                index != 0
                                            }
                                )
                            } else {
                                isAdList = false
                                actualPatientModel.copy(
                                    lipidProfile = listOf(newLipidProfile) +
                                            actualPatientModel.lipidProfile
                                )
                            }
                        )
                    })
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun SimpleGrayLine() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        drawLine(
            color = Color.Gray,
            start = Offset(0f, 0f),
            end = Offset(size.width, size.height),
            strokeWidth = 2f,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CreatePatientScreenPreview() {
    LipidOkTheme {
//        Screen(
//            isNavigationIconClick = {},
//            onEvent = {},
//        )
    }
}

internal fun getNewFirstLipidProfile(value: List<LipidProfile>): LipidProfile {
    return if (value.isNotEmpty()) {
        value.first()
    } else {
        LipidProfile(
            id = 0L,
            cholesterol = 0.0F,
            lpnp = 0.0F,
            lpvp = 0.0F,
            triglycerols = 0.0F,
            atherogenicIndex = 0.0F,
        )
    }
}