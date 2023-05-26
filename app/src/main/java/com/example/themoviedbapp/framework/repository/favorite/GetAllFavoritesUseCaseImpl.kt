package com.example.themoviedbapp.framework.repository.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.model.MovieDomain
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity
import com.example.core.model.entities.MovieWithGenreEntity
import javax.inject.Inject

class GetAllFavoritesUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : GetAllFavoritesUseCase {

    override fun invoke(): LiveData<List<MovieWithGenreEntity>> =
        repository.getAllFavorites()

}