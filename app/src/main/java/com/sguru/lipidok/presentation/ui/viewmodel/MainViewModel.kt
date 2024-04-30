package com.sguru.lipidok.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sguru.lipidok.domain.interactor.MainInteractor
import com.sguru.lipidok.domain.model.LipidProfileModel
import com.sguru.lipidok.domain.model.PatientModel
import com.sguru.lipidok.presentation.ui.model.LipidProfileQuestionsResult
import com.sguru.lipidok.presentation.ui.model.LipidProfileResult
import com.sguru.lipidok.presentation.ui.model.LipidRiskGroupType
import com.sguru.lipidok.presentation.ui.model.ScreenEvent
import com.sguru.lipidok.presentation.ui.viewmodel.factory.MainFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MainViewModel : ViewModel() {

    private val interactor = MainInteractor()
    private val factory = MainFactory()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _patients = MutableStateFlow<List<PatientModel>>(listOf())
    val patients = _patients.asStateFlow()

    private val _patientInfo = MutableStateFlow<Pair<PatientModel, List<LipidProfileModel>>?>(null)
    val patientInfo = _patientInfo.asStateFlow()

    private val _lipidProfileQuestionsResult =
        MutableStateFlow(factory.getReadyLipidProfileQuestionsResult())

    val lipidProfileQuestionsResult = _lipidProfileQuestionsResult.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            _isLoading.value = false
        }
    }

    fun getListPatient() {
        viewModelScope.launch {
            Log.d("MyTest", ">>> interactor.getPatients() ${interactor.getPatients()}")
            _patients.value = interactor.getPatients()
        }
    }

    fun onEvent(event: ScreenEvent) {
        when (event) {
            is ScreenEvent.CreatePatient -> {
                onEventCreatePatient(event)
            }

            is ScreenEvent.DataBase -> {
                onEventDataBasePatient(event)
            }

            is ScreenEvent.EditLipidProfile -> {
                onEventEditLipidProfile(event)
            }

            is ScreenEvent.AddLipidProfile -> {
                onEventAddLipidProfile(event)
            }

            is ScreenEvent.EditPatient -> {
                onEventEditPatient(event)
            }
        }
    }

    fun getLipidProfileQuestionsResult(): LipidProfileResult {
        return factory.getReadyLipidProfileResult(getValuePoint(_lipidProfileQuestionsResult.value))
    }

    fun getLipidProfileQuestionsResult(lipidRiskGroupType: LipidRiskGroupType): LipidProfileResult {
        return factory.getReadyLipidProfileResult(lipidRiskGroupType)
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

    private fun onEventCreatePatient(event: ScreenEvent.CreatePatient) {
        when (event) {
            is ScreenEvent.CreatePatient.OnButtonClickedCreatePatient -> {
                viewModelScope.launch {
                    Log.d("MyTest", ">>>onEventCreatePatient ${event.value}")
                    interactor.savePatient(
                        value = Pair(
                            first =
                            PatientModel(
                                id = 0,
                                name = event.value.name,
                                surname = event.value.surname,
                                emias = event.value.emias,
                                riskLevel = event.value.riskLevel.text
                            ),
                            second = event.value.lipidProfile
                        )
                    )
                    getListPatient()
                }
            }
        }
    }

    private fun onEventDataBasePatient(event: ScreenEvent.DataBase) {
        when (event) {
            is ScreenEvent.DataBase.OnButtonClickedDeletePatient -> {
                viewModelScope.launch {
                    interactor.deletePatient(event.patientId)
                    _patients.value = interactor.getPatients()
                }
            }

            is ScreenEvent.DataBase.OnItemPatientClicked -> {
                viewModelScope.launch {
                    _patientInfo.value = interactor.getPatientById(event.patientId)
                }
            }

            is ScreenEvent.DataBase.OnButtonEditLipidProfileClicked -> {

            }

            is ScreenEvent.DataBase.OnButtonEditPatientClicked -> {

            }
        }
    }

    private fun onEventEditLipidProfile(event: ScreenEvent.EditLipidProfile) {
        when (event) {
            is ScreenEvent.EditLipidProfile.OnButtonClickedSave -> {
                viewModelScope.launch {
                    interactor.updateLipidProfile(event.lipidProfileModel)
                    _patientInfo.value?.first?.id?.let {
                        _patientInfo.value = interactor.getPatientById(it)
                    }
                }
            }
        }
    }

    private fun onEventAddLipidProfile(event: ScreenEvent.AddLipidProfile) {
        when (event) {
            is ScreenEvent.AddLipidProfile.OnButtonClickedCreateLipidProfile -> {
                viewModelScope.launch {
                    interactor.saveLipidProfile(event.lipidProfileModel)
                    _patientInfo.value?.first?.id?.let {
                        _patientInfo.value = interactor.getPatientById(it)
                    }
                }
            }
        }
    }

    private fun onEventEditPatient(event: ScreenEvent.EditPatient) {
        when (event) {
            is ScreenEvent.EditPatient.OnButtonClickEditPatient -> {
                viewModelScope.launch {
                    interactor.updatePatient(patientModel = event.patientModel)
                    _patientInfo.value?.first?.id?.let {
                        _patientInfo.value = interactor.getPatientById(it)
                    }
                }
            }
        }
    }

    private fun getValuePoint(list: List<LipidProfileQuestionsResult>): Int {
        return list.sumOf {
            it.valuePoints ?: 0
        }
    }
}