package com.seantaylorlane.translocclient

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.seantaylorlane.translocclient.api.TranslocModels

class AgenciesViewModel : ViewModel() {
    val agenciesRepo: AgenciesRepository by lazy { AgenciesRepository() }
    val agencies: LiveData<List<TranslocModels.AgenciesResponse.Agency>> by lazy {
        agenciesRepo.getAgencies()
    }
}