package com.example.themoviedbapp.framework.repository.favorite

import com.example.core.model.MovieDomain
import javax.inject.Inject

class SaveFavoriteUseCaseImpl @Inject constructor(
    private val repository: FavoriteRepository
) : SaveFavoriteUseCase {

    override fun invoke(movie: MovieDomain) =
        repository.saveFavorite(movie)

}