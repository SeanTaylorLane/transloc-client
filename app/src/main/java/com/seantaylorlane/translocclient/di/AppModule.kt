package com.seantaylorlane.translocclient.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.seantaylorlane.translocclient.BuildConfig
import com.seantaylorlane.translocclient.api.TranslocService
import com.seantaylorlane.translocclient.db.AgenciesDao
import com.seantaylorlane.translocclient.db.Database
import com.seantaylorlane.translocclient.repository.AgenciesRepository
import com.seantaylorlane.translocclient.ui.screens.agencies.AgenciesViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideAgenciesViewModelFactory(agenciesRepository: AgenciesRepository): AgenciesViewModelFactory {
        return AgenciesViewModelFactory(agenciesRepository)
    }

    @Provides
    @Singleton
    fun provideAgenciesRepository(agenciesDao: AgenciesDao, translocService: TranslocService): AgenciesRepository {
        return AgenciesRepository(agenciesDao, translocService)
    }

    @Provides
    @Singleton
    fun provideAgenciesDao(applicationContext: Context): AgenciesDao {
        return Room.databaseBuilder(applicationContext, Database::class.java, "database")
                .build()
                .agenciesDao()
    }

    @Provides
    @Singleton
    fun provideTranslocService(): TranslocService {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor {
                    val request = it.request().newBuilder()
                            .header("X-Mashape-Key", BuildConfig.TranslocAPIKey)
                            .header("X-Mashape-Host", "transloc-api-1-2.p.mashape.com")
                            .build()
                    it.proceed(request)
                }.build()
        return Retrofit.Builder()
                .baseUrl("https://transloc-api-1-2.p.mashape.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(TranslocService::class.java)
    }

    @Provides
    fun provideApplicationContext(): Context = application.applicationContext
}