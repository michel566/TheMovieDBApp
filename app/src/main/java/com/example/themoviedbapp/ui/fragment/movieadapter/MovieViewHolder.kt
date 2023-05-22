package com.example.themoviedbapp.ui.fragment.movieadapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.R

abstract class MovieViewHolder(
    itemBinding: ViewBinding,
    private val movieCallback: (movie: MovieDomain) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    open fun bind(movie: MovieDomain) {
        itemView.setOnClickListener {
            movieCallback.invoke(movie)
        }
    }

    fun setupImage(imagePath: String?, view: ImageView) =
        Glide.with(itemView.context)
            .load(imagePath)
            .centerCrop()
            .fallback(R.drawable.baseline_broken)
            .into(view)

}