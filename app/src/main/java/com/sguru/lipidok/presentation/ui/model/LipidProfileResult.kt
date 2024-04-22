package com.sguru.lipidok.presentation.ui.model

import android.content.ClipDescription
import androidx.compose.runtime.Immutable

@Immutable
data class LipidProfileResult(
    val resultTitle: String,
    val lipidRiskGroupType: LipidRiskGroupType,
    val description: String,
)

enum class LipidRiskGroupType(val text: String) {
    VERT_TALL("очень высокая"),
    HIGH("высокая"),
    MEDIUM("средняя"),
    LOW("низкая")
}