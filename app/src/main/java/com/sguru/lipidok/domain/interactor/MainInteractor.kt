package com.sguru.lipidok.domain.interactor

import android.util.Log
import com.sguru.lipidok.data.repository.MainRepository
import com.sguru.lipidok.domain.model.LipidProfileModel
import com.sguru.lipidok.domain.model.PatientModel
import com.sguru.lipidok.presentation.ui.model.LipidProfile

internal class MainInteractor {
    private val repository = MainRepository()

    internal suspend fun savePatient(value: Pair<PatientModel, List<LipidProfile>>) {
        Log.d("MyTest", ">>> savePatient")
        repository.savePatient(value.first)
        val idPatient = repository.getPatientByNameAndSurnameAndEmias(
            name = value.first.name,
            surname = value.first.surname,
            emias = value.first.emias,
        )
        repository.saveLipidProfile(
            value.second.map {
                LipidProfileModel(
                    patientId = idPatient.id,
                    cholesterol = it.cholesterol.toString(),
                    lpnp = it.lpnp.toString(),
                    lpvp = it.lpvp.toString(),
                    triglycerols = it.triglycerols.toString(),
                    atherogenicIndex = it.atherogenicIndex.toString()
                )
            }
        )
    }

    internal suspend fun getPatients(): List<PatientModel> {
        return repository.getListPatient()
    }

    internal suspend fun getPatientById(id: Long): Pair<PatientModel, List<LipidProfileModel>> {
        return Pair(repository.getPatient(id), repository.getListLipidProfile(id))
    }

    internal suspend fun deletePatient(patientId: Long) {
        Log.d("MyTest", ">>> deletePatient $patientId")
        repository.deletePatient(patientId)
        repository.deleteLipidProfile(patientId)
    }
}