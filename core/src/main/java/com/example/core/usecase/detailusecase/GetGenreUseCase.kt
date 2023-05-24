package com.example.core.usecase.detailusecase

import com.example.core.model.Genre

interface GetGenreUseCase {
    suspend operator fun invoke(): List<Genre>
}