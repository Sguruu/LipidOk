package com.sguru.lipidok.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sguru.lipidok.data.db.contract.LipidProfileContract


@Entity(tableName = LipidProfileContract.TABLE_NAME)
data class LipidProfileEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(LipidProfileContract.Columns.ID)
    val id: Long = 0,
    @ColumnInfo(name = LipidProfileContract.Columns.PATIENT_ID)
    val patientId: Long,
    @ColumnInfo(name = LipidProfileContract.Columns.CHOLESTEROL)
    val cholesterol: String,
    @ColumnInfo(name = LipidProfileContract.Columns.LPNP)
    val lpnp: String,
    @ColumnInfo(name = LipidProfileContract.Columns.LPVP)
    val lpvp: String,
    @ColumnInfo(name = LipidProfileContract.Columns.TRIGLYCEROLS)
    val triglycerols: String,
    @ColumnInfo(name = LipidProfileContract.Columns.ATHEROGENIC_INDEX)
    val atherogenicIndex: String,
)