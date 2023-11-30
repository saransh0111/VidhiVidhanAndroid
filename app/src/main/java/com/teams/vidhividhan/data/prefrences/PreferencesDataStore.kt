package com.teams.vidhividhan.data.prefrences

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "VidhiVidhanData")

public class PreferencesDataStore(private val context: Context) {
    private suspend fun saveBoolean(key: String, value: Boolean) {
        val dataStorePreferencesKey = booleanPreferencesKey(key)
        context.dataStore.edit {
            it[dataStorePreferencesKey] = value
            Log.d("dataStore", value.toString())
        }
    }

    private suspend fun readBoolean(key: String): Boolean {
        val dataStorePreferencesKey = booleanPreferencesKey(key)

        Log.d("dataStore", "1")
        var data = false
        val pref = context.dataStore.data.map {
            Log.d("dataStore", "2")
            it[dataStorePreferencesKey] ?: false
        }
        pref.collect() {
            data = it
        }
        return data
    }

    suspend fun setFirstVisit(value: Boolean) {
        saveBoolean(IS_FIRST_VISIT, value)
    }

    suspend fun getFirstVisit(): Boolean {
        val a = readBoolean(IS_FIRST_VISIT)
        Log.d("Data", a.toString())
        return a

    }

    companion object {
        private val IS_FIRST_VISIT = "FIST_VISIT"
    }

}