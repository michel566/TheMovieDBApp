package com.example.themoviedbapp.framework.network.api

import com.example.core.model.Genre
import com.example.core.model.GenreResponse
import com.example.themoviedbapp.framework.network.response.DataWrapperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): DataWrapperResponse

    @GET("3/genre/movie/list")
    suspend fun getMovieList(): Response<GenreResponse>

}