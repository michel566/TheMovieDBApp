package com.example.themoviedbapp.ui.fragment.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.databinding.ItemFavoriteBinding
import com.example.themoviedbapp.ui.fragment.movieadapter.MovieViewHolder
import com.example.themoviedbapp.util.Utils

class FavoriteViewHolder(
    itemBinding: ItemFavoriteBinding,
    movieCallback: (movie: MovieDomain) -> Unit
) : MovieViewHolder(itemBinding, movieCallback) {

    private val image = itemBinding.image
    private val name = itemBinding.name
    private val favorite = itemBinding.ivFavorite

    private var isFavorite = false

    override fun bind(movie: MovieDomain) {
        setupImage(movie.fullPosterPath, image)
        name.text = movie.title
        isFavorite = movie.isFavorite
        Utils.setFavoriteButton(favorite, isFavorite)

        super.bind(movie)
    }


    companion object {
        fun create(
            parent: ViewGroup,
            movieCallback: (movie: MovieDomain) -> Unit
        ): FavoriteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemFavoriteBinding.inflate(inflater, parent, false)
            return FavoriteViewHolder(itemBinding, movieCallback)
        }
    }

}