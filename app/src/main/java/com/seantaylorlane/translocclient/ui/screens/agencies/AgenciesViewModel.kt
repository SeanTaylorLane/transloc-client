package com.seantaylorlane.translocclient.ui.screens.agencies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.seantaylorlane.translocclient.repository.AgenciesRepository
import com.seantaylorlane.translocclient.api.TranslocModels
import javax.inject.Inject

class AgenciesViewModel @Inject constructor(val agenciesRepository: AgenciesRepository): ViewModel() {
    val agencies: LiveData<List<TranslocModels.AgenciesResponse.Agency>> by lazy {
        agenciesRepository.getAgencies()
    }
}