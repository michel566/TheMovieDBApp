package com.example.themoviedbapp.framework.repository

import com.example.core.data.GenreRepository
import com.example.core.model.Genre
import com.example.core.model.GenreResponse
import com.example.themoviedbapp.framework.cache.CacheJsonSerializer
import com.example.themoviedbapp.framework.cache.KeyCacheConstants.CACHE_GENRE_RESPONSE
import com.example.themoviedbapp.framework.cache.TMDBAppCache
import com.example.themoviedbapp.framework.network.api.TMDBApi
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val api: TMDBApi
) : GenreRepository {

    override suspend fun fetchGenres(): List<Genre> {
        var response: GenreResponse? = null

        if (TMDBAppCache.contains(CACHE_GENRE_RESPONSE)) {
            CacheJsonSerializer.getObj(
                CACHE_GENRE_RESPONSE,
                GenreResponse::class.java
            )?.let { cacheResponse ->
                response = cacheResponse
            }
        } else {
            val apiResponse = api.getMovieList()
            if (apiResponse.isSuccessful) {
                apiResponse.body()?.let { it ->
                    response = it
                    CacheJsonSerializer.saveObj(CACHE_GENRE_RESPONSE, it)
                } ?: kotlin.run {
                    Exception("No content")
                }
            }
        }

        return getListGenre(response)
    }

    private fun getListGenre(response: GenreResponse?): List<Genre> {
        var list: List<Genre> = listOf()
        response?.genres?.let {
            list = it
        } ?: kotlin.run {
            Exception("Empty list")
        }
        return list
    }

}