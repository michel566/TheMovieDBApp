package com.example.themoviedbapp.util

import android.widget.ImageView
import com.example.core.model.MovieDomain

object Utils {

    fun setFavoriteButton(movie: MovieDomain, view: ImageView, isFavorite: Boolean){
        var isEnabled = isFavorite
        view.isEnabled = isEnabled
        view.setOnClickListener {
            isEnabled = !isEnabled
            view.isEnabled = isEnabled

//            if(isEnabled)
//                FavoriteCache.addFavoriteId(movie.id)
//            else
//                FavoriteCache.removeFavoriteId(movie.id)
        }
    }

}