package com.sguru.lipidok.presentation.ui.model

data class IndividualSelectionQuestion(
    // может быть как вопросом так и ответом
    val text: String,
    val answerOptions: List<IndividualSelectionQuestion>?
)