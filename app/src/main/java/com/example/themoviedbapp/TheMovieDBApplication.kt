package com.example.themoviedbapp

import android.app.Application
import com.example.themoviedbapp.framework.cache.TMDBAppCache
import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.HawkBuilder
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheMovieDBApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        TMDBAppCache.start(this)
    }
}