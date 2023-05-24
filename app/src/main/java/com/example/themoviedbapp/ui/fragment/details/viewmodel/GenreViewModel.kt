package com.example.themoviedbapp.ui.fragment.details.viewmodel

import androidx.lifecycle.ViewModel
import com.example.core.usecase.detailusecase.GetGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val genreUseCase: GetGenreUseCase
) : ViewModel() {

    suspend fun getGenreList() = genreUseCase.invoke()

}