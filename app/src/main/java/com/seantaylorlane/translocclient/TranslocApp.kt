package com.seantaylorlane.translocclient

import android.app.Application
import com.seantaylorlane.translocclient.di.AppComponent
import com.seantaylorlane.translocclient.di.AppModule
import com.seantaylorlane.translocclient.di.DaggerAppComponent

class TranslocApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        val appModule = AppModule(this)
        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .build()
    }
}