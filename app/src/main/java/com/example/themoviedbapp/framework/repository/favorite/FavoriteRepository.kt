package com.example.themoviedbapp.framework.repository.favorite

import androidx.lifecycle.LiveData
import com.example.core.model.MovieDomain
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity

interface FavoriteRepository {
    fun saveFavorite(movie: MovieDomain)
    fun getAllFavorites(): Pair<LiveData<List<MovieEntity>>, LiveData<List<GenreEntity>>>
    fun deleteMovieById(movieId: Int, genreList: List<Int>)
}