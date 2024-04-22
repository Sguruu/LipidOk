package com.sguru.lipidok.presentation.ui.screen

import android.util.Log
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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.component.ButtonLipidOk
import com.sguru.lipidok.presentation.ui.component.ExposedDropdownMenuLipidOk
import com.sguru.lipidok.presentation.ui.component.ExposedDropdownMenuLipidOkV2
import com.sguru.lipidok.presentation.ui.component.ScaffoldBottomBarLipidOk
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.model.IndividualSelectionQuestion
import com.sguru.lipidok.presentation.ui.theme.Grey

@Composable
internal fun IndividualSelectionOfTherapyScreen(
    individualSelectionQuestion: IndividualSelectionQuestion,
    isNavigationIconClick: () -> Unit,
    onButtonCompleteClick: () -> Unit,
) {
    var enableButton by remember { mutableStateOf(false) }
    var currentIndividualSelectionQuestion by remember { mutableStateOf(individualSelectionQuestion) }
    var currentIndexQuestion by remember { mutableIntStateOf(0) }
    var selectedOptionText by remember { mutableStateOf("Не выбрано") }
    Screen(
        isNavigationIconClick = isNavigationIconClick,
        isContinueClick = {
            if (currentIndividualSelectionQuestion.answerOptions != null) {
                Log.d("MyTest", ">>>currentIndexQuestion $currentIndexQuestion")
                currentIndividualSelectionQuestion.answerOptions?.let {
                    it[currentIndexQuestion.dec()].answerOptions?.let {
                        currentIndividualSelectionQuestion = it[0]
                    }
                }
                selectedOptionText = "Не выбрано"
                if (currentIndividualSelectionQuestion.answerOptions != null) {
                    enableButton = false
                }
            } else {
                onButtonCompleteClick()
            }
        },
        answerUser = { s, index ->
            selectedOptionText = s
            enableButton = true
            currentIndexQuestion = index
        },
        individualSelectionQuestion = currentIndividualSelectionQuestion,
        enableButton = enableButton,
        selectedOptionText = selectedOptionText
    )
}

@Composable
internal fun Screen(
    isNavigationIconClick: () -> Unit,
    isContinueClick: () -> Unit,
    answerUser: (String, Int) -> Unit,
    individualSelectionQuestion: IndividualSelectionQuestion,
    enableButton: Boolean,
    selectedOptionText: String,
) {
    Log.d("MyTest", ">>>Screen")
    Scaffold(
        topBar = {
            TopBarLipidOk(
                stringResTitle = R.string.IndividualSelectionOfTherapyScreen,
                isNavigationIconClick = isNavigationIconClick
            )
        },
        content = { paddingValue ->
            ScaffoldContent(
                paddingValue = paddingValue,
                answerUser = answerUser,
                individualSelectionQuestion = individualSelectionQuestion,
                selectedOptionText = selectedOptionText,
            )
        },
        bottomBar = {
            ScaffoldBottomBarLipidOk(
                textButton = "Продолжить",
                onButtonClick = isContinueClick,
                enableButton = enableButton,
            )
        }
    )
}

@Composable
private fun ScaffoldContent(
    paddingValue: PaddingValues,
    answerUser: (String, Int) -> Unit,
    individualSelectionQuestion: IndividualSelectionQuestion,
    selectedOptionText: String,
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
                if (individualSelectionQuestion.answerOptions != null) {
                    Text20LipidOk(text = "Ответьте на вопросы")
                } else {
                    Text20LipidOk(text = "Рекомендации")
                }

            }
            if (individualSelectionQuestion.answerOptions != null) {
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                    Text20LipidOk(text = individualSelectionQuestion.text)
                }
                item {
                    Spacer(modifier = Modifier.height(60.dp))
                    individualSelectionQuestion.answerOptions?.map {
                        it.text
                    }?.let {
                        ExposedDropdownMenuLipidOkV2(
                            getSelectedOption = { s, i ->
                                answerUser(s, i)
                            },
                            listOptions = it,
                            selectedOptionText = selectedOptionText
                        )
                    }
                }
            } else {
                item {
                    Spacer(modifier = Modifier.height(200.dp))
                    RoundedDescription(description = individualSelectionQuestion.text)
                }
            }

        }
    }
}

