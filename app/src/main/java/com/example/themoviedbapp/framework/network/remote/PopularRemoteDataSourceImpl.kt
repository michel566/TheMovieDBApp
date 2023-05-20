package com.example.themoviedbapp.framework.network.remote

import com.example.core.data.RemoteDataSource
import com.example.themoviedbapp.framework.network.api.TMDBApi
import com.example.themoviedbapp.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class PopularRemoteDataSourceImpl @Inject constructor(
    private val api: TMDBApi
) : RemoteDataSource<DataWrapperResponse> {

    override suspend fun fetchPopular(
        language: String,
        page: Int
    ): DataWrapperResponse =
        api.getPopularMovies(language, page)

}