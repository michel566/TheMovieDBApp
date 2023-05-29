package com.example.core.model.entities

import androidx.room.Embedded
import androidx.room.Relation

class MovieWithGenreEntity (
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "movieId"
    )
    val listGenres: List<GenreEntity>
)