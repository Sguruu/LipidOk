package com.sguru.lipidok.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sguru.lipidok.data.db.dao.PatientDao
import com.sguru.lipidok.data.db.database.PatientDataBase.Companion.DB_VERSION
import com.sguru.lipidok.data.db.model.LipidProfileEntity
import com.sguru.lipidok.data.db.model.PatientEntity

@Database(
    entities = [PatientEntity::class, LipidProfileEntity::class],
    version = DB_VERSION
)
abstract class PatientDataBase : RoomDatabase() {
    abstract fun PatientDao(): PatientDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "APP_ROOM_DB"
    }
}
