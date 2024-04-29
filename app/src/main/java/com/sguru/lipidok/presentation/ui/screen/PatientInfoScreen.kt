package com.sguru.lipidok.presentation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.domain.model.LipidProfileModel
import com.sguru.lipidok.domain.model.PatientModel
import com.sguru.lipidok.presentation.ui.component.ButtonLipidOk
import com.sguru.lipidok.presentation.ui.component.GrayLineLipidOk
import com.sguru.lipidok.presentation.ui.component.OutlinedTextFieldOutputLipidOk
import com.sguru.lipidok.presentation.ui.component.Text14LipidOk
import com.sguru.lipidok.presentation.ui.component.Text16LipidOk
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.model.LipidProfileResult
import com.sguru.lipidok.presentation.ui.model.LipidRiskGroupType
import com.sguru.lipidok.presentation.ui.theme.Brown
import com.sguru.lipidok.presentation.ui.theme.Green
import com.sguru.lipidok.presentation.ui.theme.Grey
import com.sguru.lipidok.presentation.ui.theme.Red
import com.sguru.lipidok.presentation.ui.theme.Yellow
import com.sguru.lipidok.presentation.ui.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun PatientInfoScreen(
    viewModel: MainViewModel,
    isNavigationIconClick: () -> Unit,
    lipidProfileResult: LipidProfileResult,
) {
    val patientInfo = viewModel.patientInfo.value
    patientInfo?.let {
        Screen(
            isNavigationIconClick = isNavigationIconClick,
            patientInfo = it,
            lipidProfileResult = lipidProfileResult,
        )
    }
}

@Composable
private fun Screen(
    isNavigationIconClick: () -> Unit,
    patientInfo: Pair<PatientModel, List<LipidProfileModel>>,
    lipidProfileResult: LipidProfileResult,
) {
    Scaffold(
        topBar = {
            TopBarLipidOk(
                stringResTitle = R.string.PatientInfoScreen,
                isNavigationIconClick = isNavigationIconClick
            )
        },
        content = { paddingValue ->
            Content(
                paddingValue = paddingValue,
                patientInfo = patientInfo,
                lipidProfileResult = lipidProfileResult,
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
                    onClick = {
                        //Редактировать
                    },
                    text = "Редактировать"
                )
            }
        },
    )
}

@Composable
private fun Content(
    paddingValue: PaddingValues,
    patientInfo: Pair<PatientModel, List<LipidProfileModel>>,
    lipidProfileResult: LipidProfileResult,
) {
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
                OutlinedTextFieldOutputLipidOk(
                    labelText = "Имя",
                    text = patientInfo.first.name
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldOutputLipidOk(
                    labelText = "Фамилия",
                    text = patientInfo.first.surname
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldOutputLipidOk(
                    labelText = "Номер пациента ЕМИАС",
                    text = patientInfo.first.emias
                )
                Spacer(modifier = Modifier.height(32.dp))
                GrayLineLipidOk()
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text20LipidOk(
                    text = "Уровень риска развития гиперлипидемии",
                    textAlign = TextAlign.Center
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text14LipidOk(
                    text = "Рассчитан на основе введенных данных",
                    textAlign = TextAlign.Center
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                val colorResult = when (lipidProfileResult.lipidRiskGroupType) {
                    LipidRiskGroupType.VERT_TALL -> {
                        Brown
                    }

                    LipidRiskGroupType.HIGH -> {
                        Red
                    }

                    LipidRiskGroupType.MEDIUM -> {
                        Yellow
                    }

                    LipidRiskGroupType.LOW -> {
                        Green
                    }
                }
                RoundedTitle(
                    title = lipidProfileResult.resultTitle,
                    result = lipidProfileResult.lipidRiskGroupType.text,
                    colorResult = colorResult
                )
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
                RoundedDescription(description = lipidProfileResult.description)
                Spacer(modifier = Modifier.height(36.dp))
                GrayLineLipidOk()
            }
            item {
                Spacer(modifier = Modifier.height(36.dp))
                Text20LipidOk(
                    text = "Данные липидного профиля",
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(36.dp))
                if (patientInfo.second.isNotEmpty()) {
                    LazyRow {
                        patientInfo.second.forEach {
                            item {
                                RoundedLipidItem(
                                    lipidProfile = it
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                } else {
                    Text16LipidOk(
                        text = "Данные липидного профиля отсутствуют",
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            item {
                RoundedLipidAdd()
                Spacer(modifier = Modifier.height(36.dp))
            }
        }
    }
}

@Composable
internal fun RoundedLipidItem(
    lipidProfile: LipidProfileModel,
) {
    Box(
        modifier = Modifier
            .width(370.dp)
            .sizeIn(minHeight = 70.dp)
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(Grey)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextFieldOutputLipidOk(
                labelText = "Общий холестерин",
                text = lipidProfile.cholesterol
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextFieldOutputLipidOk(
                labelText = "ЛПНП",
                text = lipidProfile.lpnp
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextFieldOutputLipidOk(
                labelText = "ЛПВП",
                text = lipidProfile.lpvp
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextFieldOutputLipidOk(
                labelText = "Триглицериды",
                text = lipidProfile.triglycerols
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextFieldOutputLipidOk(
                labelText = "Индекс атерогенности",
                text = lipidProfile.atherogenicIndex
            )
            Text14LipidOk(
                text = "Редактировать",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 24.dp)
                    .clickable {

                    }
            )
        }
    }
}

@Composable
internal fun RoundedLipidAdd() {
    Box(
        modifier = Modifier
            .width(370.dp)
            .sizeIn(minHeight = 70.dp)
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(Grey)
            .clickable {

            },
        contentAlignment = Alignment.CenterStart
    ) {
        Text14LipidOk(
            text = "Добавить данные липидного профиля",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .heightIn(min = 24.dp)
        )
    }
}