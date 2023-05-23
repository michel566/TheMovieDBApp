package com.example.themoviedbapp.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.DBConstants
import com.example.themoviedbapp.framework.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TMDBDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MovieEntity)

    @Query("SELECT * FROM ${DBConstants.MOVIE_TABLE_NAME}")
    suspend fun getAllPhotos(): Flow<List<MovieEntity>>

    @Query("DELETE FROM ${DBConstants.MOVIE_TABLE_NAME} WHERE id = :id")
    suspend fun deleteById(id: Int)

}