package com.example.themoviedbapp.framework.repository.favorite

import androidx.lifecycle.LiveData
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity
import javax.inject.Inject

class GetAllFavoritesUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : GetAllFavoritesUseCase {

    override fun invoke(): Pair<LiveData<List<MovieEntity>>, LiveData<List<GenreEntity>>> =
        repository.getAllFavorites()

}