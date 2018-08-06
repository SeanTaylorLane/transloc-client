package com.seantaylorlane.translocclient.di

import android.app.Application
import android.content.Context
import com.seantaylorlane.translocclient.repository.AgenciesRepository
import com.seantaylorlane.translocclient.ui.screens.agencies.AgenciesViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    fun provideAgenciesViewModelFactory(agenciesRepository: AgenciesRepository): AgenciesViewModelFactory {
        return AgenciesViewModelFactory(agenciesRepository)
    }

    @Provides
    @Singleton
    fun provideAgenciesRepository(applicationContext: Context) = AgenciesRepository(applicationContext)

    @Provides
    fun provideApplicationContext() = application.applicationContext
}