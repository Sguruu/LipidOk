package com.sguru.lipidok.data.repository

import androidx.room.Query
import com.sguru.lipidok.data.db.DataBase
import com.sguru.lipidok.data.db.contract.LipidProfileContract
import com.sguru.lipidok.data.db.model.LipidProfileEntity
import com.sguru.lipidok.data.db.model.PatientEntity
import com.sguru.lipidok.domain.model.LipidProfileModel
import com.sguru.lipidok.domain.model.PatientModel

internal class MainRepository {
    private val dataBase = DataBase.instance.PatientDao()

    internal suspend fun savePatient(value: PatientModel) {
        dataBase.insertPatient(
            PatientEntity(
                user = PatientEntity.User(
                    name = value.name,
                    surname = value.surname,
                    emias = value.emias,
                ),
                riskHyperlipidemia = PatientEntity.RiskHyperlipidemia(
                    riskGroup = value.riskLevel,
                    recommendations = "",
                )
            )
        )
    }

    internal suspend fun updatePatient(value: PatientModel) {
        dataBase.updatePatient(
            id = value.id,
            name = value.name,
            surname = value.surname,
            emias = value.surname,
            riskLevel = value.riskLevel,
        )
    }

    internal suspend fun saveLipidProfile(lipidProfileModels: List<LipidProfileModel>) {
        val lipidProfilesEntity = lipidProfileModels.map {
            LipidProfileEntity(
                patientId = it.patientId,
                cholesterol = it.cholesterol,
                lpnp = it.lpnp,
                lpvp = it.lpvp,
                triglycerols = it.triglycerols,
                atherogenicIndex = it.atherogenicIndex,
            )
        }
        dataBase.insertLipidProfile(lipidProfilesEntity)
    }

    internal suspend fun saveLipidProfile(lipidProfileModels: LipidProfileModel) {
        val lipidProfilesEntity =
            LipidProfileEntity(
                patientId = lipidProfileModels.patientId,
                cholesterol = lipidProfileModels.cholesterol,
                lpnp = lipidProfileModels.lpnp,
                lpvp = lipidProfileModels.lpvp,
                triglycerols = lipidProfileModels.triglycerols,
                atherogenicIndex = lipidProfileModels.atherogenicIndex,
            )
        dataBase.insertLipidProfile(lipidProfilesEntity)
    }

    internal suspend fun updateLipidProfile(lipidProfileModel: LipidProfileModel) {
        dataBase.updateLipidProfile(
            id = lipidProfileModel.id,
            cholesterol = lipidProfileModel.cholesterol,
            lpnp = lipidProfileModel.lpnp,
            lpvp = lipidProfileModel.lpvp,
            triglycerols = lipidProfileModel.triglycerols,
            atherogenicIndex = lipidProfileModel.atherogenicIndex,
        )
    }

    internal suspend fun getListLipidProfile(patientID: Long): List<LipidProfileModel> {
        return dataBase.getListLipidProfile(patientID).map {
            LipidProfileModel(
                id = it.id,
                patientId = it.patientId,
                cholesterol = it.cholesterol,
                lpnp = it.lpnp,
                lpvp = it.lpvp,
                triglycerols = it.triglycerols,
                atherogenicIndex = it.atherogenicIndex,
            )
        }
    }

    //getPatientByNameAndSurnameAndEmias

    internal suspend fun getPatientByNameAndSurnameAndEmias(
        name: String,
        surname: String,
        emias: String,
    ): PatientModel {
        return dataBase.getPatientByNameAndSurnameAndEmias(
            name, surname, emias
        ).let {
            PatientModel(
                id = it.id,
                name = it.user.name,
                surname = it.user.surname,
                emias = it.user.emias,
                riskLevel = it.riskHyperlipidemia.riskGroup
            )
        }

    }

    internal suspend fun getPatient(id: Long): PatientModel {
        return dataBase.getPatientById(id).let {
            PatientModel(
                id = it.id,
                name = it.user.name,
                surname = it.user.surname,
                emias = it.user.emias,
                riskLevel = it.riskHyperlipidemia.riskGroup
            )
        }
    }

    internal suspend fun getListPatient(): List<PatientModel> {
        return dataBase.getPatientList().map {
            PatientModel(
                id = it.id,
                name = it.user.name,
                surname = it.user.surname,
                emias = it.user.emias,
                riskLevel = it.riskHyperlipidemia.riskGroup
            )
        }
    }

    internal suspend fun deletePatient(id: Long) {
        dataBase.deletePatient(id)
    }

    internal suspend fun deleteLipidProfile(patientId: Long) {
        dataBase.deleteLipidProfile(patientId)
    }

    internal suspend fun getObjectLipidProfileCount(patientId: Long): Int {
        return dataBase.getObjectLipidProfileCount(patientId)
    }

    internal suspend fun deleteFirstLipidProfile(patientId: Long) {
        dataBase.deleteFirstLipidProfile(patientId)
    }
}


///**
// * Метод добавления пациента
// */
//@Insert(onConflict = OnConflictStrategy.REPLACE)
//suspend fun insertPatient(patient: PatientEntity)
//
///**
// * Получение пациента по id
// */
//@Query("SELECT * FROM ${PatientContract.TABLE_NAME} WHERE ${PatientContract.Columns.ID} = :id")
//suspend fun getPatientById(id: Long): PatientEntity
//
///**
// * Получение всех пациентов
// */
//@Query("SELECT * FROM ${PatientContract.TABLE_NAME}")
//suspend fun getPatientById(): List<PatientEntity>
//
//
///**
// * Метод добавлени липидного профиля
// */
//@Insert()
//suspend fun insertLipidProfile(lipidProfile: List<LipidProfileEntity>)
//
///**
// * Получение списка липидного профиля по id
// */
//@Query(
//    "SELECT * FROM ${LipidProfileContract.TABLE_NAME} WHERE ${LipidProfileContract.Columns.PATIENT_ID} = " +
//            ":patientID"
//)
//suspend fun getListLipidProfile(patientID: Long): List<LipidProfileEntity>