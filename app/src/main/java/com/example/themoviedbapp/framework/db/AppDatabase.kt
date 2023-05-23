package com.example.themoviedbapp.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themoviedbapp.framework.db.dao.TMDBDao
import com.example.themoviedbapp.framework.db.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun tmdbAppDao() : TMDBDao
}