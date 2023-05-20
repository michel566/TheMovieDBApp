package com.example.themoviedbapp.framework.network.response.mapper

import com.example.core.model.SrcDomain
import com.example.themoviedbapp.BuildConfig

object SrcDomainMapper {

    private const val TINY = "w92"
    private const val LITTLE = "w154"
    private const val MEDIUM = "w185"
    private const val BIG = "w342"
    private const val LARGE = "w500"
    private const val HUGE = "w780"
    private const val ORIGINAL = "original"

    private const val IMAGE_REPOSITORY_URL = "http://image.tmdb.org/t/p/"

    fun imagePathToSrcDomain(path: String) =
        SrcDomain(
            IMAGE_REPOSITORY_URL + TINY + path,
            IMAGE_REPOSITORY_URL + LITTLE + path,
            IMAGE_REPOSITORY_URL + MEDIUM + path,
            IMAGE_REPOSITORY_URL + BIG + path,
            IMAGE_REPOSITORY_URL + LARGE + path,
            IMAGE_REPOSITORY_URL + HUGE + path,
            IMAGE_REPOSITORY_URL + ORIGINAL + path,
        )

}