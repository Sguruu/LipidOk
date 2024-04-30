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
     * Получение пациента по имени и фамилии и emias
     */
    @Query(
        "SELECT * FROM ${PatientContract.TABLE_NAME} WHERE " +
                "${PatientContract.Columns.User.NAME} = :name AND " +
                "${PatientContract.Columns.User.SURNAME} = :surname AND " +
                "${PatientContract.Columns.User.EMIAS} = :emias"
    )
    suspend fun getPatientByNameAndSurnameAndEmias(
        name: String,
        surname: String,
        emias: String
    ): PatientEntity

    /**
     * Получение всех пациентов
     */
    @Query("SELECT * FROM ${PatientContract.TABLE_NAME}")
    suspend fun getPatientList(): List<PatientEntity>


    /**
     * Метод добавлени липидного профиля
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLipidProfile(lipidProfile: List<LipidProfileEntity>)

    /**
     * Метод добавлени липидного профиля
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLipidProfile(lipidProfile: LipidProfileEntity)

    /**
     * Метод обновления липидного профиля
     */
//    @Update
//    suspend fun updateLipidProfile(lipidProfile: LipidProfileEntity)

    /**
     * Метод обновления липидного профиля
     */
    @Query(
        "UPDATE ${LipidProfileContract.TABLE_NAME} SET " +
                "${LipidProfileContract.Columns.CHOLESTEROL} = :cholesterol," +
                "${LipidProfileContract.Columns.LPNP} = :lpnp," +
                "${LipidProfileContract.Columns.LPVP} = :lpvp, " +
                "${LipidProfileContract.Columns.TRIGLYCEROLS} = :triglycerols, " +
                "${LipidProfileContract.Columns.ATHEROGENIC_INDEX} = :atherogenicIndex " +
                "WHERE ${LipidProfileContract.Columns.ID} IN (:id) "
    )
    suspend fun updateLipidProfile(
        cholesterol: String,
        lpnp: String,
        lpvp: String,
        triglycerols: String,
        atherogenicIndex: String,
        id: Long,
    )

    /**
     * Получение списка липидного профиля по id
     */
    @Query(
        "SELECT * FROM ${LipidProfileContract.TABLE_NAME} WHERE ${LipidProfileContract.Columns.PATIENT_ID} = " +
                ":patientID"
    )
    suspend fun getListLipidProfile(patientID: Long): List<LipidProfileEntity>

    @Query("DELETE FROM ${PatientContract.TABLE_NAME} WHERE id = :id")
    suspend fun deletePatient(id: Long)

    @Query("DELETE FROM ${LipidProfileContract.TABLE_NAME} WHERE patientId = :patientId")
    suspend fun deleteLipidProfile(patientId: Long)

    // Получение количества объектов
    @Query("SELECT COUNT(*) FROM ${LipidProfileContract.TABLE_NAME} WHERE patientId = :patientId")
    suspend fun getObjectLipidProfileCount(patientId: Long): Int

    // Удаление первого объекта из списка
    // Удаление первого объекта из списка, ориентируясь на id
    @Query(
        "DELETE FROM ${LipidProfileContract.TABLE_NAME} WHERE ${LipidProfileContract.Columns.ID} " +
                "= (SELECT MIN(${LipidProfileContract.Columns.ID}) FROM ${LipidProfileContract.TABLE_NAME} " +
                "WHERE ${LipidProfileContract.Columns.PATIENT_ID} = :patientId)"
    )
    suspend fun deleteFirstLipidProfile(patientId: Long)


//    @Query(
//        "DELETE FROM ${LipidProfileContract.TABLE_NAME} WHERE ${LipidProfileContract.Columns.ID} " +
//                "= (SELECT MIN(${LipidProfileContract.Columns.ID}) FROM ${LipidProfileContract.TABLE_NAME} " +
//                "WHERE ${LipidProfileContract.Columns.NAME} = :name)"
//    )
}