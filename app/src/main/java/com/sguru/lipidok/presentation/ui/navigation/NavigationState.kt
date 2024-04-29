package com.sguru.lipidok.presentation.ui.navigation

sealed class NavigationState(val baseRoute: String) {
    data object RoleSelectionScreen : NavigationState("ROLE_SELECTION")
    data object MainScreen : NavigationState("MAIN")
    data object DataBaseScreen : NavigationState("DATA_BASE")
    data object GeneralScreen : NavigationState("GENERAL")
    data object LipidProfileAssessmentScreen : NavigationState("LIPID_PROFILE_ASSESSMENT")
    data object LipidProfileAssessmentResultScreen :
        NavigationState("LIPID_PROFILE_ASSESSMENT_RESULT")

    data object IndividualSelectionOfTherapyScreen :
        NavigationState("INDIVIDUAL_SELECTION_OF_THERAPY")
    data object CreatePatientScreen : NavigationState("CREATE_PATIENT")
    data object PatientInfoScreen : NavigationState("PATIENT_INFO")
    data object EditLipidProfileScreen : NavigationState("EDIT_LIPID_PROFILE")
    data object PatientEditScreen : NavigationState("PATIENT_EDIT")
}