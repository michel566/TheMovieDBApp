package com.example.themoviedbapp.framework.repository.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.model.MovieDomain
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity
import com.example.core.model.entities.MovieWithGenreEntity
import com.example.themoviedbapp.framework.db.dao.TMDBDao
import com.example.themoviedbapp.util.DataMapper
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val database: TMDBDao
) : FavoriteRepository {

    private var movieEntities: List<MovieEntity>? = listOf()
    private var genreEntities: List<GenreEntity>? = listOf()

    override suspend fun saveFavorite(movie: MovieDomain) {
        database.insertMovie(DataMapper.movieDomainToEntity(movie))
        movie.genreIds.forEach {
            database.insertGenre(GenreEntity(it, movie.id))
        }
    }

    override fun getAllFavorites(): LiveData<List<MovieWithGenreEntity>> =
        database.getAllMovies()

    override suspend fun deleteMovieById(movieId: Int, genreList: List<Int>) {
        genreList.forEach {
            database.deleteGenreById(movieId, it)
        }
        database.deleteMovieById(movieId)
    }

    private fun mergeTableGenreInMovie(): List<MovieDomain>? {
        return if (!genreEntities.isNullOrEmpty() && !movieEntities.isNullOrEmpty()){
            genreEntities?.let { g ->
                movieEntities?.let { m ->
                    return DataMapper.pairOfEntitiesToListMovieDomain(m, g)
                } ?: kotlin.run { null }
            } ?: kotlin.run { null }
        } else {
            null
        }
    }

}