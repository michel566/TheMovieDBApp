package com.example.core.usecase.detailusecase

import com.example.core.data.GenreRepository
import javax.inject.Inject

class GetGenreUseCaseImpl @Inject constructor(
    private val repository: GenreRepository
) : GetGenreUseCase {

    override suspend fun invoke() = repository.fetchGenres()
}