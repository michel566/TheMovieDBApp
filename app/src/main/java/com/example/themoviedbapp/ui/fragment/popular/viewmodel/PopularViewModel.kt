package com.example.themoviedbapp.ui.fragment.popular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.model.MovieDomain
import com.example.core.usecase.popularusecase.GetPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: GetPopularUseCase
): ViewModel() {

    fun popularMovies(): Flow<PagingData<MovieDomain>> =
        popularUseCase(GetPopularUseCase.GetPopularParams(pagingConfig()))
            .cachedIn(viewModelScope)

    private fun pagingConfig() = PagingConfig(pageSize = 40)

}