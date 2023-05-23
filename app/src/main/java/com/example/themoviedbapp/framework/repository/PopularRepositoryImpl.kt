package com.example.themoviedbapp.framework.repository

import androidx.paging.PagingSource
import com.example.core.data.PopularRepository
import com.example.core.data.RemoteDataSource
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.framework.paging.PopularPagingSource
import com.example.themoviedbapp.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class PopularRepositoryImpl

 @Inject constructor(
    private val remoteDataSource: RemoteDataSource<DataWrapperResponse>
) : PopularRepository {

    override fun fetchPopular(): PagingSource<Int, MovieDomain> {
        return PopularPagingSource(dataSource = remoteDataSource)
    }
}