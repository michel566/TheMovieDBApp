package com.example.themoviedbapp.framework.di

import com.example.core.data.GenreRepository
import com.example.core.data.PopularRepository
import com.example.core.data.RemoteDataSource
import com.example.core.model.Genre
import com.example.themoviedbapp.framework.network.response.DataWrapperResponse
import com.example.themoviedbapp.framework.remote.PopularRemoteDataSourceImpl
import com.example.themoviedbapp.framework.repository.GenreRepositoryImpl
import com.example.themoviedbapp.framework.repository.PopularRepositoryImpl
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

    @Binds
    fun bindGenreRepository(repositoryImpl: GenreRepositoryImpl) : GenreRepository

}