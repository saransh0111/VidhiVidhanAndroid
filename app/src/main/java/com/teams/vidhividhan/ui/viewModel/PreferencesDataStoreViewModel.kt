package com.teams.vidhividhan.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teams.vidhividhan.data.prefrences.PreferencesDataStore
import kotlinx.coroutines.launch

class PreferencesDataStoreViewModel(private val preferencesDataStore: PreferencesDataStore) :
    ViewModel() {

    fun setFirstVisit(value: Boolean) {
        viewModelScope.launch {
            preferencesDataStore.setFirstVisit(value)
        }
    }


    fun getFirstVisit(): Boolean {
        var isFirst = true
        viewModelScope.launch {
            isFirst = preferencesDataStore.getFirstVisit()
            Log.d("Inside", preferencesDataStore.getFirstVisit().toString())
        }
        return isFirst
    }
}