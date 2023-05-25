package com.example.themoviedbapp.framework.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.DBConstants
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity

@Dao
interface TMDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(genre: GenreEntity)

    @Query("SELECT * FROM ${DBConstants.MOVIE_TABLE_NAME}")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM ${DBConstants.GENRE_TABLE_NAME}")
    fun getAllGenres(): LiveData<List<GenreEntity>>

    @Query("DELETE FROM ${DBConstants.MOVIE_TABLE_NAME} WHERE id = :id")
    fun deleteMovieById(id: Int)

    @Query("DELETE FROM ${DBConstants.GENRE_TABLE_NAME} WHERE movieId = :movieId AND id = :id")
    fun deleteGenreById(movieId: Int, id: Int)

}