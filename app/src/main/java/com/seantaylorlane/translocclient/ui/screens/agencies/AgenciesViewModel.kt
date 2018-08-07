package com.seantaylorlane.translocclient.ui.screens.agencies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.seantaylorlane.translocclient.api.Resource
import com.seantaylorlane.translocclient.repositories.AgenciesRepository
import com.seantaylorlane.translocclient.api.TranslocModels
import javax.inject.Inject

class AgenciesViewModel @Inject constructor(val agenciesRepository: AgenciesRepository): ViewModel() {
    val agencies: LiveData<Resource<List<TranslocModels.AgenciesResponse.Agency>>> by lazy {
        agenciesRepository.loadAllAgencies()
    }
}