package com.vinaylogics.restappkotlin.core

import android.app.Application
import timber.log.Timber

/**
 * Created by vinaylogics on 27-05-2017.
 */
class PostAppication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}