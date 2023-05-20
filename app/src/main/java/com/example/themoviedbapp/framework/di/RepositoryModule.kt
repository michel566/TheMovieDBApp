package com.example.themoviedbapp.framework.di

import com.example.core.data.PopularRepository
import com.example.core.data.RemoteDataSource
import com.example.themoviedbapp.framework.network.remote.PopularRemoteDataSourceImpl
import com.example.themoviedbapp.framework.network.repository.PopularRepositoryImpl
import com.example.themoviedbapp.framework.network.response.DataWrapperResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPopularRepository(repositoryImpl: PopularRepositoryImpl) : PopularRepository

    @Binds
    fun bindRemoteDataSource(dataSourceImpl: PopularRemoteDataSourceImpl): RemoteDataSource<DataWrapperResponse>

}