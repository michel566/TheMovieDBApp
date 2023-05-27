package com.example.themoviedbapp.ui.fragment.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.databinding.ItemFavoriteBinding
import com.example.themoviedbapp.ui.fragment.movieadapter.MovieViewHolder

class FavoriteViewHolder(
    itemBinding: ItemFavoriteBinding,
    private val saveFavoriteCallback: (movie: MovieDomain) -> Unit,
    private val removeFavoriteCallback: (movie: MovieDomain) -> Unit
) : MovieViewHolder(itemBinding, null) {

    private val image = itemBinding.image
    private val name = itemBinding.name
    private val favorite = itemBinding.ivFavorite

    private var isFavorite = false

    override fun bind(movie: MovieDomain) {
        setupImage(movie.fullPosterPath, image)

        name.text = movie.title
        isFavorite = movie.isFavorite
        setFavoriteButton(movie)

        super.bind(movie)
    }

    private fun setFavoriteButton(movie: MovieDomain) =
        with(favorite) {
            isHovered = isFavorite

            setOnClickListener {
                isFavorite = !isFavorite
                isHovered = isFavorite

                movie.isFavorite = isFavorite

                if (isFavorite)
                    saveFavoriteCallback.invoke(movie)
                else
                    removeFavoriteCallback.invoke(movie)
            }
        }

    companion object {
        fun create(
            parent: ViewGroup,
            saveFavoriteCallback: (movie: MovieDomain) -> Unit,
            removeFavoriteCallback: (movie: MovieDomain) -> Unit
        ): FavoriteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemFavoriteBinding.inflate(inflater, parent, false)
            return FavoriteViewHolder(
                itemBinding,
                saveFavoriteCallback,
                removeFavoriteCallback
            )
        }
    }

}