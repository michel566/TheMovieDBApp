package com.example.themoviedbapp.ui.fragment.favorites.viewmodel

import androidx.lifecycle.ViewModel
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.framework.repository.favorite.DeleteFavoriteUseCase
import com.example.themoviedbapp.framework.repository.favorite.GetAllFavoritesUseCase
import com.example.themoviedbapp.framework.repository.favorite.SaveFavoriteUseCase
import com.example.themoviedbapp.util.DataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    suspend fun saveFavorite(movie: MovieDomain) =
        saveFavoriteUseCase.invoke(movie)

    suspend fun getAllFavorites(
        onEmptyList: () -> Unit?,
        onError: () -> Unit?,
        onSuccess: (list: List<MovieDomain>) -> Unit,
    ) {
        getAllFavoritesUseCase.invoke()?.collectLatest { listEntity ->
            listEntity.let { data ->
                if (data.isNotEmpty())
                    onSuccess.invoke(data.map { DataMapper.movieWithGenreEntityToDomain(it) })
                else
                    onEmptyList.invoke()
            } ?: kotlin.run {
                onError.invoke()
            }
        } ?: kotlin.run {
            onError.invoke()
        }
    }

    suspend fun deleteFavoriteById(id: Int) {
        deleteFavoriteUseCase.invoke(id)
    }

    suspend fun filterFavorites(
        char: CharSequence,
        onEmptyList: () -> Unit?,
        onError: () -> Unit?,
        onSuccess: (list: List<MovieDomain>) -> Unit
    ) {
        getAllFavoritesUseCase.invoke()?.collectLatest { listEntity ->
            listEntity.let { data ->
                if (data.isNotEmpty()) {
                    val list = data.map { DataMapper.movieWithGenreEntityToDomain(it) }
                    onSuccess.invoke(list.filter {
                        it.title.lowercase().contains(char)
                    })
                } else
                    onEmptyList.invoke()
            } ?: kotlin.run {
                onError.invoke()
            }
        } ?: kotlin.run {
            onError.invoke()
        }
    }

}