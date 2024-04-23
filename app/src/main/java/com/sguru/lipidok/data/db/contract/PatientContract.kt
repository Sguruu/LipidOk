package com.sguru.lipidok.data.db.contract

object PatientContract {
    const val TABLE_NAME = "PatientEntity"

    object Columns {
        const val ID = "id"

        object User {
            const val NAME = "name"
            const val SURNAME = "surname"
            const val EMIAS = "emias"
        }

        object RiskHyperlipidemia {
            const val RISK_LEVEL = "riskGroup"
            const val RECOMMENDATIONS = "recommendations"
        }
    }
}

object LipidProfileContract {
    const val TABLE_NAME = "LipidProfileEntity"
    object Columns {
        const val ID = "id"
        const val PATIENT_ID = "patientId"
            const val CHOLESTEROL = "cholesterol"
            const val LPNP = "lpnp"
            const val LPVP = "lpvp"
            const val TRIGLYCEROLS = "triglycerols"
            const val ATHEROGENIC_INDEX = "atherogenicIndex"
    }
}