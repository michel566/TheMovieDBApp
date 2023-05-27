package com.example.themoviedbapp.ui.fragment.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.framework.repository.favorite.DeleteFavoriteUseCase
import com.example.themoviedbapp.framework.repository.favorite.GetAllFavoritesUseCase
import com.example.themoviedbapp.framework.repository.favorite.SaveFavoriteUseCase
import com.example.themoviedbapp.util.DataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

//    var listData: List<MovieDomain> = listOf()
//    init {
//        viewModelScope.launch {
//            getAllFavoritesUseCase.invoke()?.collectLatest { listEntity ->
//                listEntity.let { data ->
//                    if (data.isNotEmpty())
//                        listData = (data.map { DataMapper.movieWithGenreEntityToDomain(it) })
//                }
//            }
//        }
//    }
//
//    fun getList() = listData

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
            }?: kotlin.run {
                onError.invoke()
            }
        }?: kotlin.run {
            onError.invoke()
        }
    }

    suspend fun deleteFavoriteById(id: Int) {
        deleteFavoriteUseCase.invoke(id)
    }


}