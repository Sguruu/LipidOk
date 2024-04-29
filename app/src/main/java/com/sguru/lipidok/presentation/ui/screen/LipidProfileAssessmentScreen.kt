package com.sguru.lipidok.presentation.ui.screen

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.component.ButtonLipidOk
import com.sguru.lipidok.presentation.ui.component.ExposedDropdownMenuLipidOk
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.model.LipidProfileQuestions
import com.sguru.lipidok.presentation.ui.model.LipidProfileQuestionsResult
import com.sguru.lipidok.presentation.ui.theme.LipidOkTheme
import com.sguru.lipidok.presentation.ui.viewmodel.MainViewModel
import com.sguru.lipidok.presentation.ui.viewmodel.factory.MainFactory

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
internal fun LipidProfileAssessmentScreen(
    viewModel: MainViewModel,
    lipidProfileQuestions: List<LipidProfileQuestions>,
    isNavigationIconClick: () -> Unit,
    isButtonGetResultClick: () -> Unit,
) {
    //val screenState = remember { viewModel.screenState }
    val lipidProfileQuestionsResult by viewModel.lipidProfileQuestionsResult.collectAsState()
    var enableButton by remember { mutableStateOf(true) }


    Screen(
        lipidProfileQuestions = lipidProfileQuestions,
        isNavigationIconClick = isNavigationIconClick,
        isButtonGetResultClick = isButtonGetResultClick,
        updateLipidProfileQuestions = viewModel::updateLipidProfileQuestions,
        enableButton = enableButton
    )

    LaunchedEffect(lipidProfileQuestionsResult) {
       // enableButton = checkEnableButton(lipidProfileQuestionsResult)
    }
}


@Composable
private fun Screen(
    lipidProfileQuestions: List<LipidProfileQuestions>,
    isNavigationIconClick: () -> Unit,
    isButtonGetResultClick: () -> Unit,
    updateLipidProfileQuestions: (Int, String) -> Unit,
    enableButton: Boolean,
) {
    Scaffold(
        topBar = {
            TopBarLipidOk(
                stringResTitle = R.string.LipidProfileAssessmentScreen,
                isNavigationIconClick = isNavigationIconClick
            )
        },
        content = { paddingValue ->
            LipidProfileAssessmentContent(
                paddingValue = paddingValue,
                lipidProfileQuestions = lipidProfileQuestions,
                updateLipidProfileQuestions = updateLipidProfileQuestions
            )
        },
        bottomBar = {
            LipidProfileAssessmentBottomBar(
                textButton = "Получить результаты",
                onButtonResultClick = isButtonGetResultClick,
                enableButton = enableButton
            )
        }
    )
}

@Composable
private fun LipidProfileAssessmentContent(
    paddingValue: PaddingValues,
    lipidProfileQuestions: List<LipidProfileQuestions>,
    updateLipidProfileQuestions: (Int, String) -> Unit,
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
        Text20LipidOk(text = "Ответьте на вопросы")
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn() {
            lipidProfileQuestions.forEachIndexed { index, lipidProfileQuestions ->
                item {
                    ExposedDropdownMenuLipidOk(
                        textLabel = lipidProfileQuestions.questions,
                        listOptions = lipidProfileQuestions.answerOptions.map { answerOptions ->
                            answerOptions.answer
                        }
                    ) { answer ->
                        updateLipidProfileQuestions(index, answer)
                    }
                }
            }
        }
    }
}

@Composable
private fun LipidProfileAssessmentBottomBar(
    textButton: String,
    enableButton: Boolean,
    onButtonResultClick: () -> Unit
) {
    ButtonGetResult(
        textButton = textButton,
        enableButton = enableButton,
        onButtonClick = onButtonResultClick
    )
}

@Composable
private fun ButtonGetResult(
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

private fun checkEnableButton(list: List<LipidProfileQuestionsResult>): Boolean {
    Log.d("MyTest", ">>>checkEnableButton")
    for (i in 0..list.lastIndex) {
        if (list[i].valuePoints == null) {
            return false
        }
    }
    return true
}


@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    LipidOkTheme {
        Screen(
            isNavigationIconClick = {},
            isButtonGetResultClick = {},
            lipidProfileQuestions = MainFactory().getReadyLipidProfileQuestions(),
            updateLipidProfileQuestions = { _, _ -> },
            enableButton = false
        )
    }
}