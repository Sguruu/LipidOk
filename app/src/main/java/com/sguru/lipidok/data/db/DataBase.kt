package com.sguru.lipidok.data.db

import android.content.Context
import androidx.room.Room
import com.sguru.lipidok.data.db.database.PatientDataBase

object DataBase {
    lateinit var instance: PatientDataBase
        private set

    /**
     * Функция инициализатор DataBase
     * @param context контекст приложения
     */
    fun init(context: Context) {
        instance = Room.databaseBuilder(
            context,
            PatientDataBase::class.java,
            PatientDataBase.DB_NAME,
        ).build()
    }
}