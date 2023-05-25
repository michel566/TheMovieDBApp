package com.example.core.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.DBConstants

@Entity(tableName = DBConstants.MOVIE_TABLE_NAME)
data class MovieEntity (

    @PrimaryKey val id: Int,
    val isFavorite: Boolean,
    val isAdult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,

)