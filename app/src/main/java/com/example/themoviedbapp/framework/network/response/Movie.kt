package com.example.themoviedbapp.framework.network.response

import com.example.core.model.MovieDomain
import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("adult")
    val isAdult: Boolean?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    )

fun Movie.toMovieDomain(): MovieDomain =
    MovieDomain(
        isFavorite = false,
        isAdult = this.isAdult ?: false,
        backdropPath = this.backdropPath ?: "",
        genreIds = this.genreIds ?: listOf(),
        id = this.id ?: 0,
        originalLanguage = this.originalLanguage ?: "",
        originalTitle = this.originalTitle ?: "",
        overview = this.overview ?: "",
        popularity = this.popularity ?: 0.0,
        posterPath = this.posterPath ?: "",
        releaseDate = this.releaseDate ?: "",
        title = this.title ?: "",
        video = this.video ?: false,
        voteAverage = this.voteAverage ?: 0.0,
        voteCount = this.voteCount ?: 0
    )
