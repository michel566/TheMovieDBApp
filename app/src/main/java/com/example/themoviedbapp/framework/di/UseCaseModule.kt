package com.example.themoviedbapp.framework.di

import com.example.core.usecase.detailusecase.GetGenreUseCase
import com.example.core.usecase.detailusecase.GetGenreUseCaseImpl
import com.example.core.usecase.popularusecase.GetPopularUseCase
import com.example.core.usecase.popularusecase.GetPopularUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindPopularUseCase(getPopularUseCaseImpl: GetPopularUseCaseImpl): GetPopularUseCase

    @Binds
    fun bindGenreUseCase(getGenreUseCaseImpl: GetGenreUseCaseImpl): GetGenreUseCase
}