package com.sguru.lipidok.presentation.ui.model

import androidx.compose.runtime.Immutable

//data class LevelIndividualSelectionQuestions(
//    val individualSelection: List<IndividualSelection>
//)
//data class Question(val text: String, val answers: List<Question>)
data class IndividualSelectionQuestion(
    // может быть как вопросом так и ответом
    val text: String,
    val answerOptions: List<IndividualSelectionQuestion>?
)

data class IndividualSelectionAnswer(
    val answer: String
)

//sealed interface IndividualQuestions {
//    val answer: String
//
//    sealed interface VariantOneIndividualQuestions : IndividualQuestions {
//        data object VariantOne : VariantOneIndividualQuestions {
//            override val answer: String = "Нет"
//        }
////        sealed interface VariantOne : VariantOneIndividualQuestions
////        sealed interface VariantTwo : VariantOneIndividualQuestions
//    }
//
//    sealed interface VariantTwoIndividualQuestions : IndividualQuestions {
//        sealed interface VariantOne : VariantTwoIndividualQuestions
//        sealed interface VariantTwo : VariantTwoIndividualQuestions
//    }
//
//    sealed interface VariantThreeIndividualQuestions : IndividualQuestions {
//        sealed interface VariantOne : VariantThreeIndividualQuestions
//        sealed interface VariantTwo : VariantThreeIndividualQuestions
//    }
//
//    sealed interface VariantFourIndividualQuestions : IndividualQuestions {
//        sealed interface VariantOne : VariantFourIndividualQuestions
//        sealed interface VariantTwo : VariantFourIndividualQuestions
//    }
//
//    enum class LipidLoweringTherapyUsed(override val answer: String) : IndividualQuestions {
//        VARIANT_ONE("Нет"),
//        VARIANT_TWO("Статины не в максимальной дозе"),
//        VARIANT_THERE("Статины в максимальной дозе"),
//        VARIANT_FOUR("Статины + Эзетемиб"),
//    }
//}