package com.seantaylorlane.translocclient.ui.screens.agencies

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.seantaylorlane.translocclient.repository.AgenciesRepository
import javax.inject.Inject

class AgenciesViewModelFactory @Inject constructor(val agenciesRepository: AgenciesRepository) : ViewModelProvider.Factory {

    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.equals(AgenciesViewModel::class.java)) {
            return AgenciesViewModel(agenciesRepository) as T
        } else {
            throw RuntimeException("Unable to create ${modelClass::class.simpleName} with ${this::class.simpleName}")
        }
    }

}