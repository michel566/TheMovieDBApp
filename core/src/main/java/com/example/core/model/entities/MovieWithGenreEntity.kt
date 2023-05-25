package com.example.core.model.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
class MovieWithGenreEntity (
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val listGenres: List<GenreEntity>
)