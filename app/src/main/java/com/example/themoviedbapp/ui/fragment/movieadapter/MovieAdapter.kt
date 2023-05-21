package com.example.themoviedbapp.ui.fragment.movieadapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.core.model.MovieDomain

class MovieAdapter(
    private val movieCallback: ((movie: MovieDomain) -> Unit)
): PagingDataAdapter<MovieDomain, MovieViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder.create(parent, movieCallback)

    companion object{
        private val diffCallback = object : DiffUtil.ItemCallback<MovieDomain>(){
            override fun areItemsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieDomain, newItem: MovieDomain): Boolean {
                return oldItem == newItem
            }
        }
    }
}