package com.seantaylorlane.translocclient.repository

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.seantaylorlane.translocclient.api.TranslocModels
import com.seantaylorlane.translocclient.api.TranslocService
import com.seantaylorlane.translocclient.db.AgenciesDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AgenciesRepository @Inject constructor(val agenciesDao: AgenciesDao, val translocService: TranslocService) {

    fun getAgencies(): LiveData<List<TranslocModels.AgenciesResponse.Agency>> {
        refreshAgencies()
        return agenciesDao.loadAll()
    }

    fun refreshAgencies() {
        val callback = object : Callback<TranslocModels.AgenciesResponse> {
            override fun onResponse(call: Call<TranslocModels.AgenciesResponse>?, response: Response<TranslocModels.AgenciesResponse>?) {
                // TODO: Handle error cases
                val data = response?.body()?.data
                if(data != null) {
                    SaveToDatabaseAsyncTask(agenciesDao).execute(data.sortedBy { it.name })
                }
            }

            override fun onFailure(call: Call<TranslocModels.AgenciesResponse>?, t: Throwable?) {
                // TODO: Handle error cases
            }
        }
        translocService.getAgencies().enqueue(callback)
    }

    private class SaveToDatabaseAsyncTask(val agenciesDao: AgenciesDao) : AsyncTask<List<TranslocModels.AgenciesResponse.Agency>, Unit, Unit>() {
        override fun doInBackground(vararg params: List<TranslocModels.AgenciesResponse.Agency>) {
            agenciesDao.save(params[0])
        }
    }
}