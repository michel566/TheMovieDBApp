package com.example.themoviedbapp.ui.fragment.movieadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.R
import com.example.themoviedbapp.databinding.ItemMovieBinding

class MovieViewHolder(
    itemBinding: ItemMovieBinding,
    private val movieCallback: (movie: MovieDomain) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    private val image = itemBinding.image
    private val name = itemBinding.name

    fun bind(movie: MovieDomain){
        Glide.with(itemView.context)
            .load(movie.posterPath.original)
            .centerCrop()
            .fallback(R.drawable.baseline_broken)
            .into(image)

        name.text = movie.title

        itemView.setOnClickListener {
            movieCallback.invoke(movie)
        }
    }

    companion object{
        fun create(parent: ViewGroup,
                   movieCallback: (movie: MovieDomain) -> Unit): MovieViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemMovieBinding.inflate(inflater, parent, false)
            return MovieViewHolder(itemBinding, movieCallback)
        }
    }
}