package com.example.themoviedbapp.ui.fragment.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.core.model.MovieDomain
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity
import com.example.themoviedbapp.framework.repository.favorite.DeleteFavoriteUseCase
import com.example.themoviedbapp.framework.repository.favorite.GetAllFavoritesUseCase
import com.example.themoviedbapp.framework.repository.favorite.SaveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    fun saveFavorite(movie: MovieDomain) = saveFavoriteUseCase.invoke(movie)

    fun getAllFavorites(): Pair<LiveData<List<MovieEntity>>, LiveData<List<GenreEntity>>> {
        return getAllFavoritesUseCase.invoke()
    }

    fun deleteFavoriteById(id: Int, genreList: List<Int>) =
        deleteFavoriteUseCase.invoke(id, genreList)
}