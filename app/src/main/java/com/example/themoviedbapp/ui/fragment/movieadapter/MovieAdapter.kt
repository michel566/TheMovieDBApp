package com.example.themoviedbapp.ui.fragment.movieadapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.ui.fragment.favorites.FavoriteViewHolder
import com.example.themoviedbapp.ui.fragment.main.Option
import com.example.themoviedbapp.ui.fragment.popular.PopularViewHolder

class MovieAdapter(
    private val movieCallback: ((movie: MovieDomain) -> Unit),
    private val type: Option
) : PagingDataAdapter<MovieDomain, MovieViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return when (type) {
            Option.FAVORITE -> FavoriteViewHolder.create(parent, movieCallback)
            Option.POPULAR -> PopularViewHolder.create(parent, movieCallback)
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MovieDomain>() {
            override fun areItemsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
                return oldItem == newItem
            }
        }
    }
}