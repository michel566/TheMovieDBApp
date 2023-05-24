package com.example.themoviedbapp.framework.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.DBConstants
import com.example.themoviedbapp.framework.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = DBConstants.APP_DATABASE_NAME
        ).build()

    @Provides
    fun providesTMDBDao(db: AppDatabase) =
        db.tmdbAppDao()
}