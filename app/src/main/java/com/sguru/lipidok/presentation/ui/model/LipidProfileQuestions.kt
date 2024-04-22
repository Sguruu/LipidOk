package com.sguru.lipidok.presentation.ui.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class LipidProfileQuestions(
    val questions: String,
    val answerOptions: List<Answer>
)

internal data class Answer(
    val answer: String,
    val valuePoints: Int
)

// текущий выбор пользователя
internal data class LipidProfileQuestionsResult(
    val questions: String,
    val valuePoints: Int?
)
