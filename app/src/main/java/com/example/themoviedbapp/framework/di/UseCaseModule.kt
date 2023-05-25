package com.example.themoviedbapp.framework.di

import com.example.core.usecase.detailusecase.GetGenreUseCase
import com.example.core.usecase.detailusecase.GetGenreUseCaseImpl
import com.example.themoviedbapp.framework.repository.favorite.DeleteFavoriteUseCase
import com.example.themoviedbapp.framework.repository.favorite.DeleteFavoriteUseCaseImpl
import com.example.themoviedbapp.framework.repository.favorite.GetAllFavoritesUseCase
import com.example.themoviedbapp.framework.repository.favorite.GetAllFavoritesUseCaseImpl
import com.example.themoviedbapp.framework.repository.favorite.SaveFavoriteUseCase
import com.example.themoviedbapp.framework.repository.favorite.SaveFavoriteUseCaseImpl
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

    @Binds
    fun bindSaveFavoriteUseCase(saveFavoriteUseCaseImpl: SaveFavoriteUseCaseImpl): SaveFavoriteUseCase

    @Binds
    fun bindGetAllFavoriteUseCase(getAllFavoritesUseCaseImpl: GetAllFavoritesUseCaseImpl): GetAllFavoritesUseCase

    @Binds
    fun bindDeleteFavoriteUseCase(deleteFavoriteUseCaseImpl: DeleteFavoriteUseCaseImpl): DeleteFavoriteUseCase


}