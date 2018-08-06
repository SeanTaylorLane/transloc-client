package com.seantaylorlane.translocclient.di

import com.seantaylorlane.translocclient.ui.screens.agencies.AgenciesActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(agenciesActivity: AgenciesActivity)
}