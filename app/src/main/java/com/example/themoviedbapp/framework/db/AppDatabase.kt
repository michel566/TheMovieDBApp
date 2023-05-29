package com.example.themoviedbapp.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity
import com.example.themoviedbapp.framework.db.dao.TMDBDao

@Database(entities = [MovieEntity::class, GenreEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tmdbAppDao(): TMDBDao
}