package com.example.redballtoy.findyourpet

import android.app.Application
import timber.log.Timber

class FindYourPetApplication: Application(){

    //initiate analytics, crashlytics, etc
     override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

}