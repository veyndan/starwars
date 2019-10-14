package com.veyndan.starwars

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class StarWarsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
