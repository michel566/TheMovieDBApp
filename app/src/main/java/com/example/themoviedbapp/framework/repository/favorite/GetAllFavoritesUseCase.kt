package com.example.themoviedbapp.framework.repository.favorite

import androidx.lifecycle.LiveData
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity

interface GetAllFavoritesUseCase {
    operator fun invoke(): Pair<LiveData<List<MovieEntity>>, LiveData<List<GenreEntity>>>
}