package com.example.themoviedbapp.framework.repository.favorite

import com.example.core.model.MovieDomain

interface SaveFavoriteUseCase {
    suspend operator fun invoke(movie: MovieDomain)
}