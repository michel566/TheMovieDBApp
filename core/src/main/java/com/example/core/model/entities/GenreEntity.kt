package com.example.core.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.DBConstants

@Entity(tableName = DBConstants.GENRE_TABLE_NAME)
data class GenreEntity(

    @PrimaryKey val id: Int,
    val movieId: Int

)