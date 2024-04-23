package com.sguru.lipidok.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.sguru.lipidok.data.db.contract.PatientContract

@Entity(tableName = PatientContract.TABLE_NAME)
data class PatientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(PatientContract.Columns.ID)
    val id: Long = 0,
    @Embedded
    val user: User,
    @Embedded
    val riskHyperlipidemia: RiskHyperlipidemia,
) {
    data class User(
        @ColumnInfo(PatientContract.Columns.User.NAME)
        val name: String,
        @ColumnInfo(PatientContract.Columns.User.SURNAME)
        val surname: String,
        @ColumnInfo(PatientContract.Columns.User.EMIAS)
        val emias: String
    )

    data class RiskHyperlipidemia(
        @ColumnInfo(name = PatientContract.Columns.RiskHyperlipidemia.RISK_LEVEL)
        val riskGroup: String,
        @ColumnInfo(name = PatientContract.Columns.RiskHyperlipidemia.RECOMMENDATIONS)
        val recommendations: String,
    )
}

