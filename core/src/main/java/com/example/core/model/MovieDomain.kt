package com.example.core.model

import com.example.core.GeneralConstants

data class MovieDomain(
    var isFavorite: Boolean = false,
    val isAdult: Boolean = false,
    val backdropPath: String = "",
    val genreIds: List<Int>,
    val id: Int = 0,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
){
    val fullPosterPath: String
        get() = "${GeneralConstants.IMAGE_REPOSITORY_URL}$posterPath"

}
