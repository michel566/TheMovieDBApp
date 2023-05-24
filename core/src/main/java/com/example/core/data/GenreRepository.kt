package com.example.core.data

import com.example.core.model.Genre

interface GenreRepository {
    suspend fun fetchGenres(): List<Genre>
}