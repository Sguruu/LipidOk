package com.sguru.lipidok.presentation.ui.model

internal data class PatientModel(
    val name: String,
    val surname: String,
    val emias: String,
    val riskLevel: LipidRiskGroupType,
    val lipidProfile: List<LipidProfile>,
)

internal data class LipidProfile(
    val cholesterol: Float,
    val lpnp: Float,
    val lpvp: Float,
    val triglycerols: Float,
    val atherogenicIndex: Float,
)
