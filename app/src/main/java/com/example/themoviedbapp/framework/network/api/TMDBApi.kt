package com.example.themoviedbapp.framework.network.api

import com.example.themoviedbapp.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): DataWrapperResponse

}