package com.example.themoviedbapp.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.core.model.Genre
import com.example.themoviedbapp.R

object Utils {

    fun setFavoriteButton(view: ImageView, isFavorite: Boolean) {
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

    fun setupResourceImage(context: Context, view: ImageView, imagePath: String?) {
        Glide.with(context)
            .load(imagePath)
            .centerCrop()
            .fallback(R.drawable.baseline_broken)
            .into(view)
    }

    fun setGenresInText(genreList: List<Genre>, genreIds: List<Int>?): String {
        val list = getGenresName(genreList, genreIds)
        var text = ""
        var i = 0
        if (list.isNotEmpty()) {
            list.forEach {
                if (i >= list.size - 1) {
                    text += " and"
                    text += " $it"
                } else {
                    text +=
                        if (i > 0)
                            " $it,"
                        else {
                            "$it,"
                        }
                }

                i++
            }
        }
        return text
    }

    private fun getGenresName(genreList: List<Genre>, genreIds: List<Int>?): List<String> {
        val list: MutableList<String> = mutableListOf()
        genreList.forEach { genre ->
            genreIds?.forEach { id ->
                if (genre.id == id)
                    list.add(genre.name)
            }
        }
        return list.toList()
    }
}