package com.sguru.lipidok.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.component.ButtonLipidOk
import com.sguru.lipidok.presentation.ui.component.Text14LipidOk
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.model.LipidProfileResult
import com.sguru.lipidok.presentation.ui.model.LipidRiskGroupType
import com.sguru.lipidok.presentation.ui.theme.Brown
import com.sguru.lipidok.presentation.ui.theme.Green
import com.sguru.lipidok.presentation.ui.theme.Grey
import com.sguru.lipidok.presentation.ui.theme.LipidOkTheme
import com.sguru.lipidok.presentation.ui.theme.Red
import com.sguru.lipidok.presentation.ui.theme.Yellow

@Composable
internal fun LipidProfileAssessmentResultScreen(
    isNavigationIconClick: () -> Unit,
    lipidProfileResult: LipidProfileResult,
    onButtonCompleteClick: () -> Unit,
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
        bottomBar = {
            LipidProfileAssessmentResultBottomBar(
                textButton = "Завершить",
                onButtonCompleteClick = onButtonCompleteClick,
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    modifier = Modifier.size(300.dp, 180.dp),
                    painter = painterResource(id = R.drawable.image_logo),
                    contentDescription = null
                )
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
                Text14LipidOk(text = "Рассчитан на основе введенных данных")
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
            }
        }

    }
}

@Composable
fun RoundedTitle(
    title: String,
    result: String,
    colorResult: Color
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
        Row() {
            Text20LipidOk(
                textAlign = TextAlign.Center,
                text = title,
            )
            Text20LipidOk(color = colorResult, text = result)
        }
    }
}

@Composable
private fun LipidProfileAssessmentResultBottomBar(
    textButton: String,
    onButtonCompleteClick: () -> Unit,
) {
    ButtonComplete(
        textButton = textButton,
        onButtonClick = onButtonCompleteClick
    )
}

@Composable
private fun ButtonComplete(
    textButton: String,
    onButtonClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
    ) {
        ButtonLipidOk(onClick = onButtonClick, text = textButton)
    }
}

@Composable
internal fun RoundedDescription(
    description: String,
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
        Text20LipidOk(
            text = description,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    LipidOkTheme {
        RoundedTitle("Привет", " Пока", Green)
    }
}