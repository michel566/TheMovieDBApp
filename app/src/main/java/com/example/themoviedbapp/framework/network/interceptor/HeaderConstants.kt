package com.example.themoviedbapp.framework.network.interceptor

object HeaderConstants {
    const val NAME_REQUEST_FILE = "accept"
    const val TYPE_REQUEST_FILE = "application/json"
    const val NAME_TOKEN_FILE = "Authorization"

    fun concatToken (token: String) = "Bearer $token"
}