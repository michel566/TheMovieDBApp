package com.example.themoviedbapp.framework.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieDetailDomain(
    val id: Int? = 0,
    var isFavorite: Boolean = false,
    val imagePath: String = "",
    val genresId: List<Int>?,
    val originalTitle: String? = "",
    val overview: String? = "",
    val releaseDate: String? = "",
    val title: String? = "",
    val isAdult: Boolean = false,
    val backdropPath: String = "",
    val originalLanguage: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
) : Parcelable