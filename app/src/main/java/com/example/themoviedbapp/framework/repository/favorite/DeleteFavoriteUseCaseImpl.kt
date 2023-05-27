package com.example.themoviedbapp.framework.repository.favorite

import javax.inject.Inject

class DeleteFavoriteUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : DeleteFavoriteUseCase {
    override suspend fun invoke(id: Int) {
        repository.deleteMovieById(id)
    }
}