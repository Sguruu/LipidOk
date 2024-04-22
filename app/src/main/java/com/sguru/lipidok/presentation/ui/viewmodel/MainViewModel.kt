package com.sguru.lipidok.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sguru.lipidok.presentation.ui.model.LipidProfileQuestionsResult
import com.sguru.lipidok.presentation.ui.model.LipidProfileResult
import com.sguru.lipidok.presentation.ui.viewmodel.factory.MainFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MainViewModel : ViewModel() {

    private val factory = MainFactory()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _lipidProfileQuestionsResult =
        MutableStateFlow(factory.getReadyLipidProfileQuestionsResult())

    val lipidProfileQuestionsResult = _lipidProfileQuestionsResult.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            _isLoading.value = false
        }
    }

    fun getLipidProfileQuestionsResult(): LipidProfileResult {
        return factory.getReadyLipidProfileResult(getValuePoint(_lipidProfileQuestionsResult.value))
    }

    fun updateLipidProfileQuestions(index: Int, valueAnswer: String) {
        val lipidProfileQuestions = factory.getReadyLipidProfileQuestions()

        for (i in 0..lipidProfileQuestions[index].answerOptions.lastIndex) {
            if (lipidProfileQuestions[index].answerOptions[i].answer == valueAnswer) {
                _lipidProfileQuestionsResult.update { list ->
                    buildList {
                        list.forEachIndexed { currentIndex, lipidProfileQuestionsResult ->
                            if (index == currentIndex) {
                                add(
                                    LipidProfileQuestionsResult(
                                        questions = list[currentIndex].questions,
                                        valuePoints = lipidProfileQuestions[index]
                                            .answerOptions[i].valuePoints
                                    )
                                )
                            } else {
                                add(
                                    LipidProfileQuestionsResult(
                                        questions = list[currentIndex].questions,
                                        valuePoints = list[currentIndex].valuePoints
                                    )
                                )
                            }

                        }
                    }
                }
            }
        }
    }

    fun clearLipidProfileQuestions() {
        _lipidProfileQuestionsResult.update {
            factory.getReadyLipidProfileQuestionsResult()
        }
    }

    private fun getValuePoint(list: List<LipidProfileQuestionsResult>): Int {
        return list.sumOf {
            it.valuePoints ?: 0
        }
    }
}