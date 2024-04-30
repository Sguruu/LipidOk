package com.sguru.lipidok.presentation.ui.model

import com.sguru.lipidok.domain.model.LipidProfileModel

internal sealed interface ScreenEvent {

    interface CreatePatient : ScreenEvent {
        data class OnButtonClickedCreatePatient(val value: PatientModel) : CreatePatient
    }

    interface DataBase : ScreenEvent {
        data class OnButtonClickedDeletePatient(val patientId: Long) : DataBase
        data class OnItemPatientClicked(val patientId: Long) : DataBase
        data class OnButtonEditLipidProfileClicked(val lipidId: Long) : DataBase
        data class OnButtonEditPatientClicked(val patientId: Long) : DataBase
    }

    interface EditLipidProfile : ScreenEvent {
        data class OnButtonClickedSave(val lipidProfileModel: LipidProfileModel) : EditLipidProfile
    }

    interface AddLipidProfile : ScreenEvent {
        data class OnButtonClickedCreateLipidProfile(val lipidProfileModel: LipidProfileModel) :
            AddLipidProfile
    }

    interface EditPatient : ScreenEvent {
        data class OnButtonClickEditPatient(val patientModel: com.sguru.lipidok.domain.model.PatientModel) :
            EditPatient
    }

}