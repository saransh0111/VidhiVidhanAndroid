package com.teams.vidhividhan.baseApplication

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        startKoin {
            androidContext(this@BaseApplication)
//            modules(listOf(persistenceModule, viewModelModule, apiModule, repoModule))
        }


//        appEventsLogger  = AppEventsLogger.newLogger(this)
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
//        appEventsLogger  = AppEventsLogger.newLogger(context)


    }

    companion object {
        var context: BaseApplication? = null

//        var mFirebaseAnalytics: FirebaseAnalytics? = null
//        var appEventsLogger: AppEventsLogger? = null

    }
}