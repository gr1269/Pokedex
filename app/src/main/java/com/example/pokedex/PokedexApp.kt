package com.example.pokedex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PokedexApp: Application() {

    override fun onCreate() {
        super.onCreate()

        configTimber()

    }

    private fun configTimber() {
        Timber.plant(Timber.DebugTree())
    }
}