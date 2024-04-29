package com.sguru.lipidok.presentation.ui.model

internal sealed interface ScreenEvent {

    interface CreatePatient : ScreenEvent {
        data class OnButtonClickedCreatePatient(val value: PatientModel) : CreatePatient
    }

    interface DataBase : ScreenEvent {
        data class OnButtonClickedDeletePatient(val patientId: Long) : DataBase
        data class OnItemPatientClicked(val patientId: Long) : DataBase
    }

}