package com.sguru.lipidok.domain.model

internal data class PatientModel(
    val id: Long,
    val name: String,
    val surname: String,
    val emias: String,
    val riskLevel: String,
)