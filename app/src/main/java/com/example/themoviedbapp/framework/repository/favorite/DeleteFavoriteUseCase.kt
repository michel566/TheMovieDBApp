package com.example.themoviedbapp.framework.repository.favorite

interface DeleteFavoriteUseCase {
    suspend operator fun invoke(id: Int, genreList: List<Int>)
}