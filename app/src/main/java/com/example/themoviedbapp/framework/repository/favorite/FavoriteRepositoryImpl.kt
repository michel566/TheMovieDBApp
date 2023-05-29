package com.example.themoviedbapp.framework.repository.favorite

import com.example.core.model.MovieDomain
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieWithGenreEntity
import com.example.themoviedbapp.framework.db.dao.TMDBDao
import com.example.themoviedbapp.util.DataMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val database: TMDBDao
) : FavoriteRepository {

    override suspend fun saveFavorite(movie: MovieDomain) {
        database.insertMovie(DataMapper.movieDomainToEntity(movie))
        movie.genreIds.forEach {
            database.insertGenre(GenreEntity(it, movie.id))
        }
        getAllFavorites()
    }

    override fun getAllFavorites(): Flow<List<MovieWithGenreEntity>>? =
        database.getAllMovies()

    override suspend fun deleteMovieById(movieId: Int) {
        database.deleteGenreById(movieId)
        database.deleteMovieById(movieId)
        getAllFavorites()
    }

}