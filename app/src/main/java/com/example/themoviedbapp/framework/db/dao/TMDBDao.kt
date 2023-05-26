package com.example.themoviedbapp.framework.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.data.DBConstants
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity
import com.example.core.model.entities.MovieWithGenreEntity

@Dao
interface TMDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: GenreEntity)

    @Transaction
    @Query("SELECT * FROM ${DBConstants.MOVIE_TABLE_NAME}")
    fun getAllMovies(): LiveData<List<MovieWithGenreEntity>>

    @Query("DELETE FROM ${DBConstants.MOVIE_TABLE_NAME} WHERE movieId = :id")
    suspend fun deleteMovieById(id: Int)

    @Query("DELETE FROM ${DBConstants.GENRE_TABLE_NAME} WHERE movieId = :movieId AND id = :id")
    suspend fun deleteGenreById(movieId: Int, id: Int)

}