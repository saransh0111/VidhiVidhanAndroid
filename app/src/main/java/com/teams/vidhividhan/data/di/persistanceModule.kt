package com.teams.vidhividhan.data.di


import com.teams.vidhividhan.data.prefrences.PreferencesDataStore
import com.teams.vidhividhan.data.prefrences.SharedPrefs
import com.teams.vidhividhan.utils.glide.GlideImageLoader
import org.koin.dsl.module



/**
 * Use the [persistenceModule] to creating shared preference instance
// **/
val persistenceModule = module {

    /**
     * Singleton for shared preference
     **/
    single { PreferencesDataStore(get()) }
    single { SharedPrefs(get()) }
    single { GlideImageLoader(get()) }


}