package com.example.themoviedbapp.framework.repository.favorite

import androidx.lifecycle.LiveData
import com.example.core.model.MovieDomain
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity
import com.example.themoviedbapp.framework.db.dao.TMDBDao
import com.example.themoviedbapp.util.DataMapper
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val database: TMDBDao
) : FavoriteRepository {

    override fun saveFavorite(movie: MovieDomain) {
        database.insertMovie(DataMapper.movieDomainToEntity(movie))
        movie.genreIds.forEach {
            database.insertGenre(GenreEntity(it, movie.id))
        }
    }

    override fun getAllFavorites(): Pair<LiveData<List<MovieEntity>>, LiveData<List<GenreEntity>>> {
        return Pair(
            database.getAllMovies(),
            database.getAllGenres()
        )
    }

    override fun deleteMovieById(movieId: Int, genreList: List<Int>) {
        genreList.forEach {
            database.deleteGenreById(movieId, it)
        }
        database.deleteMovieById(movieId)
    }

}