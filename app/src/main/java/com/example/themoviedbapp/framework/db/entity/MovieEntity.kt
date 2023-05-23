package com.example.themoviedbapp.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.DBConstants

@Entity(tableName = DBConstants.MOVIE_TABLE_NAME)
data class MovieEntity(

    @PrimaryKey
    val id: Int,

    val imageUrl: String,
    val title: String,
    val isFavorite: Boolean
)
