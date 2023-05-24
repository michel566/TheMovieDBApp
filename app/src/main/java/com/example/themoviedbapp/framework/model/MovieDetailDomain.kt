package com.example.themoviedbapp.framework.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieDetailDomain(
    val id: Int? = 0,
    val isFavorite: Boolean = false,
    val imagePath: String = "",
    val genresId: List<Int>?,
    val originalTitle: String? = "",
    val overview: String? = "",
    val releaseDate: String? = "",
    val title: String? = ""
) : Parcelable