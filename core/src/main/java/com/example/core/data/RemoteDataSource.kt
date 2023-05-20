package com.example.core.data

interface RemoteDataSource <T> {
    suspend fun fetchPopular(language: String, page: Int): T
}