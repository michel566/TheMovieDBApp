package com.example.themoviedbapp

import android.app.Application
import com.example.core.data.DBConstants
import com.example.themoviedbapp.framework.cache.TMDBAppCache
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheMovieDBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TMDBAppCache.start(this)
    }

    override fun onTerminate() {
        TMDBAppCache.wipeOut()
//        applicationContext.deleteDatabase(DBConstants.APP_DATABASE_NAME)
        super.onTerminate()
    }
}