package com.seantaylorlane.translocclient

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.os.AsyncTask
import android.widget.Toast
import com.seantaylorlane.translocclient.api.TranslocModels
import com.seantaylorlane.translocclient.api.TranslocService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AgenciesRepository(val application: Application) {
    val agenciesDao: AgenciesDao by lazy {
        Room.databaseBuilder(application.applicationContext, Database::class.java, "database")
                .build()
                .agenciesDao()
    }

    val translocService: TranslocService by lazy {
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
        Retrofit.Builder()
                .baseUrl("https://transloc-api-1-2.p.mashape.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(TranslocService::class.java)
    }

    fun getAgencies(): LiveData<List<TranslocModels.AgenciesResponse.Agency>> {
        refreshAgencies()
        return agenciesDao.loadAll()
    }

    fun refreshAgencies() {
        val data = MutableLiveData<List<TranslocModels.AgenciesResponse.Agency>>()
        val callback = object : Callback<TranslocModels.AgenciesResponse> {
            override fun onResponse(call: Call<TranslocModels.AgenciesResponse>?, response: Response<TranslocModels.AgenciesResponse>?) {
                // TODO: Handle error cases
                val data = response?.body()?.data
                SaveToDatabaseAsyncTask(agenciesDao).execute(data)
            }

            override fun onFailure(call: Call<TranslocModels.AgenciesResponse>?, t: Throwable?) {
                // TODO: Handle error cases
                Toast.makeText(application.applicationContext, "Network error", Toast.LENGTH_SHORT)
            }
        }
        translocService.getAgencies().enqueue(callback)
    }

    private class SaveToDatabaseAsyncTask(val agenciesDao: AgenciesDao) : AsyncTask<List<TranslocModels.AgenciesResponse.Agency>, Unit, Unit>() {
        override fun doInBackground(vararg params: List<TranslocModels.AgenciesResponse.Agency>?) {
            val data = params[0]
            if(data != null) {
                agenciesDao.save(data)
            }
        }
    }
}