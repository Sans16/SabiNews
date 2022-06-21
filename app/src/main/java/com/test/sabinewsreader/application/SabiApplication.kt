package com.test.sabinewsreader.application

import android.app.Application
import com.test.sabinewsreader.BuildConfig
import com.test.sabinewsreader.helpers.HeapRemover
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SabiApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree()) else Timber.plant(HeapRemover())
    }
}