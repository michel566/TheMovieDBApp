package com.example.themoviedbapp.util

import com.example.core.model.MovieDomain
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity
import com.example.core.model.entities.MovieWithGenreEntity
import com.example.themoviedbapp.framework.model.MovieDetailDomain

object DataMapper {

    fun movieEntityToDomain(
        entity: MovieEntity,
        genreListId: List<Int>
    ) =
        MovieDomain(
            isFavorite = entity.isFavorite,
            isAdult = entity.isAdult,
            backdropPath = entity.backdropPath,
            genreIds = genreListId,
            id = entity.movieId,
            originalLanguage = entity.originalLanguage,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            popularity = entity.popularity,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            video = entity.video,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount
        )

    fun movieDomainToEntity(domain: MovieDomain) =
        MovieEntity(
            isFavorite = domain.isFavorite,
            isAdult = domain.isAdult,
            backdropPath = domain.backdropPath,
            movieId = domain.id,
            originalLanguage = domain.originalLanguage,
            originalTitle = domain.originalTitle,
            overview = domain.overview,
            popularity = domain.popularity,
            posterPath = domain.posterPath,
            releaseDate = domain.releaseDate,
            title = domain.title,
            video = domain.video,
            voteAverage = domain.voteAverage,
            voteCount = domain.voteCount
        )


    fun movieDomainToDetail(domain: MovieDomain) =
        MovieDetailDomain(
            id = domain.id,
            isFavorite = domain.isFavorite,
            imagePath = domain.fullPosterPath,
            genresId = domain.genreIds,
            originalTitle = domain.originalTitle,
            overview = domain.overview,
            releaseDate = domain.releaseDate,
            title = domain.title,
            isAdult = domain.isAdult,
            backdropPath = domain.backdropPath,
            originalLanguage = domain.originalLanguage,
            popularity = domain.popularity,
            posterPath = domain.posterPath,
            video = domain.video,
            voteAverage = domain.voteAverage,
            voteCount = domain.voteCount
        )

    fun movieDetailToDomain(detail: MovieDetailDomain) =
        MovieDomain(
            id = detail.id ?: 0,
            isFavorite = detail.isFavorite,
            isAdult = detail.isAdult,
            backdropPath = detail.backdropPath,
            genreIds = detail.genresId ?: listOf(),
            originalLanguage = detail.originalLanguage,
            originalTitle = detail.originalTitle ?: "",
            overview = detail.overview ?: "",
            popularity = detail.popularity,
            posterPath = detail.posterPath,
            releaseDate = detail.releaseDate ?: "",
            title = detail.title ?: "",
            video = detail.video,
            voteAverage = detail.voteAverage,
            voteCount = detail.voteCount
        )


    fun movieWithGenreEntityToDomain(entity: MovieWithGenreEntity) =
        MovieDomain(
            id = entity.movie.movieId ,
            isFavorite = entity.movie.isFavorite,
            isAdult = entity.movie.isAdult,
            backdropPath = entity.movie.backdropPath,
            genreIds = entity.listGenres.map { it.movieId },
            originalLanguage = entity.movie.originalLanguage,
            originalTitle = entity.movie.originalTitle,
            overview = entity.movie.overview,
            popularity = entity.movie.popularity,
            posterPath = entity.movie.posterPath,
            releaseDate = entity.movie.releaseDate,
            title = entity.movie.title,
            video = entity.movie.video,
            voteAverage = entity.movie.voteAverage,
            voteCount = entity.movie.voteCount
        )

    fun pairOfEntitiesToListMovieDomain(
        movieList: List<MovieEntity>, genreList: List<GenreEntity>
    )
            : List<MovieDomain> {
        return movieList.map { movie ->
            genreList.map { genre ->
                if (genre.movieId == movie.movieId)
                    genre.id
                else
                    0
            }.let { idList ->
                movieEntityToDomain(movie, idList)
            }
        }
    }


}