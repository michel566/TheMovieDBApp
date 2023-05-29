package com.example.themoviedbapp.ui.fragment.popular.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.ui.fragment.favorites.FavoriteViewHolder

class FavoriteAdapter(
    private val saveFavoriteCallback: (movie: MovieDomain) -> Unit,
    private val removeFavoriteCallback: (movie: MovieDomain) -> Unit
) :
    ListAdapter<MovieDomain, FavoriteViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder.create(
            parent,
            saveFavoriteCallback,
            removeFavoriteCallback
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MovieDomain>() {
            override fun areItemsTheSame(
                oldItem: MovieDomain,
                newItem: MovieDomain
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: MovieDomain,
                newItem: MovieDomain
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}
