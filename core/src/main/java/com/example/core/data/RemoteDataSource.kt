package com.example.core.data

interface RemoteDataSource <T> {
    suspend fun fetchPopular(page: Int): T
}