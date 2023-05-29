package com.example.themoviedbapp.ui.fragment.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.GeneralConstants
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.databinding.ItemPopularBinding
import com.example.themoviedbapp.ui.fragment.movieadapter.MovieViewHolder
import com.example.themoviedbapp.util.Utils

class PopularViewHolder(
    itemBinding: ItemPopularBinding,
    movieCallback: (movie: MovieDomain) -> Unit
) : MovieViewHolder(itemBinding, movieCallback) {

    private val image = itemBinding.image
    private val name = itemBinding.name
    private val date = itemBinding.date
    private val description = itemBinding.description

    override fun bind(movie: MovieDomain) {
        setupImage(movie.fullPosterPath, image)

        name.text = movie.title
        date.text =  Utils.parseDateToddMMyyyy(
            GeneralConstants.PATTERN_YYYY_MM_DD,
            movie.releaseDate,
            GeneralConstants.PATTERN_DD_MM_YYYY
        )
        description.text = movie.overview

        super.bind(movie)
    }

    companion object {
        fun create(
            parent: ViewGroup,
            movieCallback: (movie: MovieDomain) -> Unit
        ): PopularViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPopularBinding.inflate(inflater, parent, false)
            return PopularViewHolder(itemBinding, movieCallback)
        }
    }

}