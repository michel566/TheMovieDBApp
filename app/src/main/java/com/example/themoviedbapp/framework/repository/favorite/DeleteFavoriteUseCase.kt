package com.example.themoviedbapp.framework.repository.favorite

interface DeleteFavoriteUseCase {
    operator fun invoke(id: Int, genreList: List<Int>)
}