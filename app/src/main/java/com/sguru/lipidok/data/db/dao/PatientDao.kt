package com.sguru.lipidok.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sguru.lipidok.data.db.contract.LipidProfileContract
import com.sguru.lipidok.data.db.contract.PatientContract
import com.sguru.lipidok.data.db.model.LipidProfileEntity
import com.sguru.lipidok.data.db.model.PatientEntity

@Dao
interface PatientDao {
    /**
     * Метод добавления пациента
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patient: PatientEntity)

    /**
     * Получение пациента по id
     */
    @Query("SELECT * FROM ${PatientContract.TABLE_NAME} WHERE ${PatientContract.Columns.ID} = :id")
    suspend fun getPatientById(id: Long): PatientEntity

    /**
     * Получение всех пациентов
     */
    @Query("SELECT * FROM ${PatientContract.TABLE_NAME}")
    suspend fun getPatientById(): List<PatientEntity>


    /**
     * Метод добавлени липидного профиля
     */
    @Insert()
    suspend fun insertLipidProfile(lipidProfile: List<LipidProfileEntity>)

    /**
     * Получение списка липидного профиля по id
     */
    @Query(
        "SELECT * FROM ${LipidProfileContract.TABLE_NAME} WHERE ${LipidProfileContract.Columns.PATIENT_ID} = " +
                ":patientID"
    )
    suspend fun getListLipidProfile(patientID: Long): List<LipidProfileEntity>
}