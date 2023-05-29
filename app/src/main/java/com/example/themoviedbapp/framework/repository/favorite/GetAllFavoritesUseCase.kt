package com.example.themoviedbapp.framework.repository.favorite

import com.example.core.model.entities.MovieWithGenreEntity
import kotlinx.coroutines.flow.Flow

interface GetAllFavoritesUseCase {
    operator fun invoke(): Flow<List<MovieWithGenreEntity>>?
}