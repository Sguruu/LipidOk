package com.sguru.lipidok

import android.app.Application
import android.content.res.Resources
import com.sguru.lipidok.data.db.DataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        res = this.resources
        DataBase.init(this)
    }

    companion object {
        lateinit var res: Resources
            private set
    }

}