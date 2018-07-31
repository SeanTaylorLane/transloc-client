package com.seantaylorlane.translocclient

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.seantaylorlane.translocclient.api.TranslocModels

class AgenciesViewModel(app: Application) : AndroidViewModel(app) {
    val agenciesRepo: AgenciesRepository by lazy { AgenciesRepository(app) }
    val agencies: LiveData<List<TranslocModels.AgenciesResponse.Agency>> by lazy {
        agenciesRepo.getAgencies()
    }
}