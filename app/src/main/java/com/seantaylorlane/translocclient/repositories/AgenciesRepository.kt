package com.seantaylorlane.translocclient.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.AsyncTask
import com.seantaylorlane.translocclient.api.Resource
import com.seantaylorlane.translocclient.api.TranslocModels
import com.seantaylorlane.translocclient.api.TranslocService
import com.seantaylorlane.translocclient.db.AgenciesDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class AgenciesRepository @Inject constructor(val agenciesDao: AgenciesDao, val translocService: TranslocService) {

    fun loadAllAgencies(): LiveData<Resource<List<TranslocModels.AgenciesResponse.Agency>>> {
        val result = MediatorLiveData<Resource<List<TranslocModels.AgenciesResponse.Agency>>>()
        result.value = Resource.loading()
        val dbSource = agenciesDao.loadAll()
        result.addSource(dbSource) { data ->
            if(data != null && !data.isEmpty()) {
                result.value = Resource.success(data)
            }
            result.removeSource(dbSource)
            refreshAllAgencies(result)
        }
        return result
    }

    fun refreshAllAgencies(result: MediatorLiveData<Resource<List<TranslocModels.AgenciesResponse.Agency>>>) {
        translocService.getAgencies().enqueue(object : Callback<TranslocModels.AgenciesResponse> {
            override fun onResponse(call: Call<TranslocModels.AgenciesResponse>?, response: Response<TranslocModels.AgenciesResponse>?) {
                val data = response?.body()?.data?.sortedBy { it.name }
                val message = response?.body()?.message
                if(data != null && response.isSuccessful) {
                    result.value = Resource.success(data)
                    SaveToDatabaseAsyncTask(agenciesDao).execute(data)
                } else if(message != null) {
                    result.value = Resource.failure(message)
                } else {
                    result.value = Resource.failure("Couldn't refresh agencies (unknown error)")
                }
            }

            override fun onFailure(call: Call<TranslocModels.AgenciesResponse>?, t: Throwable?) {
                if(t is IOException) {
                    result.value = Resource.failure("Couldn't refresh agencies (no internet)")
                } else {
                    result.value = Resource.failure("Deserialization error! Please file a error report and try again later")
                }
            }
        })
    }

    private class SaveToDatabaseAsyncTask(val agenciesDao: AgenciesDao) : AsyncTask<List<TranslocModels.AgenciesResponse.Agency>, Unit, Unit>() {
        override fun doInBackground(vararg params: List<TranslocModels.AgenciesResponse.Agency>) {
            agenciesDao.save(params[0])
        }
    }
}