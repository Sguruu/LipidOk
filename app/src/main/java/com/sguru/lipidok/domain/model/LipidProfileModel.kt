package com.sguru.lipidok.domain.model

internal data class LipidProfileModel(
    val id: Long,
    val patientId: Long,
    val cholesterol: String,
    val lpnp: String,
    val lpvp: String,
    val triglycerols: String,
    val atherogenicIndex: String,
)