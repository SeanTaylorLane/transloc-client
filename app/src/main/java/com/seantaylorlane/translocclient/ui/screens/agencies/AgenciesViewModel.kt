package com.seantaylorlane.translocclient.ui.screens.agencies

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.seantaylorlane.translocclient.repository.AgenciesRepository
import com.seantaylorlane.translocclient.api.TranslocModels

class AgenciesViewModel(app: Application) : AndroidViewModel(app) {
    val agenciesRepo: AgenciesRepository by lazy { AgenciesRepository(getApplication()) }
    val agencies: LiveData<List<TranslocModels.AgenciesResponse.Agency>> by lazy {
        agenciesRepo.getAgencies()
    }
}