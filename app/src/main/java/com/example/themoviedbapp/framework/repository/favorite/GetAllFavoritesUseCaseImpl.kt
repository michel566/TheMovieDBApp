package com.example.themoviedbapp.framework.repository.favorite

import com.example.core.model.entities.MovieWithGenreEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoritesUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : GetAllFavoritesUseCase {

    override fun invoke(): Flow<List<MovieWithGenreEntity>>? =
        repository.getAllFavorites()
}