package com.example.themoviedbapp.ui.fragment.popular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.core.model.MovieDomain
import com.example.core.usecase.popularusecase.GetPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: GetPopularUseCase
) : ViewModel() {

    fun popularMovies(): Flow<PagingData<MovieDomain>> =
        popularUseCase(GetPopularUseCase.GetPopularParams(pagingConfig()))
            .cachedIn(viewModelScope)

//    fun favoriteMovies(): Flow<PagingData<MovieDomain>> {
//        return popularMovies().map { pagingData ->
//            pagingData.filter { movie -> movie.isFavorite }
//        }
//    }

    private fun pagingConfig() = PagingConfig(pageSize = 40)


}