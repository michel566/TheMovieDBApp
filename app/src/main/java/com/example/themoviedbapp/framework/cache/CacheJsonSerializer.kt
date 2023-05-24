package com.example.themoviedbapp.framework.cache

import com.google.gson.Gson

object CacheJsonSerializer {
    fun <T> saveList(key: String, list: List<T>?) {
        val gson = Gson()
        val json = gson.toJson(list)
        TMDBAppCache.update(key, value = json)
    }

    fun <T> saveObj (key: String, obj: T?) {
        val gson = Gson()
        val json = gson.toJson(obj)
        TMDBAppCache.update(key, value = json)
    }

    fun <T> getObjList(key: String, clazz: Class<Array<T>>?): List<T> {
        val json = TMDBAppCache.get<String>(key)
        val array = Gson().fromJson(json, clazz)
        return if (array != null) listOf(*array) else ArrayList()
    }

    fun <T> getObj(key: String, clazz: Class<T>?): T? {
        val json = TMDBAppCache.get<String>(key)
        return Gson().fromJson(json, clazz)
    }

}