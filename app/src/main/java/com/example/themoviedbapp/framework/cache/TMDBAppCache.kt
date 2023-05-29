package com.example.themoviedbapp.framework.cache

import android.content.Context
import com.orhanobut.hawk.Hawk

object TMDBAppCache {

    fun start(context: Context) =
        Hawk.init(context).build()

    fun <T> insert(key: String, value: T?) =
        Hawk.put(key, value)

    fun <T> update(key: String, value: T?) {
        if (Hawk.contains(key)) {
            Hawk.delete(key)
        }
        insert(key, value)
    }

    fun delete(key: String?) =
        Hawk.delete(key)

    fun <T> get(key: String): T =
        Hawk.get(key)

    fun contains(key: String): Boolean =
        Hawk.contains(key)

    fun wipeOut() {
        Hawk.deleteAll()
    }
}