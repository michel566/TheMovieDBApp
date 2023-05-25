package com.example.themoviedbapp.ui.fragment.popular.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.ui.fragment.favorites.FavoriteViewHolder
import okhttp3.internal.notifyAll

class FavoriteAdapter(
    private val movieCallback: ((movie: MovieDomain) -> Unit)
) : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var mList: List<MovieDomain>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return FavoriteViewHolder.create(parent, movieCallback)
    }

    override fun getItemCount() = if (mList.isEmpty()) 0 else mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        if (holder is FavoriteViewHolder)
            holder.bind(item)
    }

    fun updateList(list: List<MovieDomain>?) {
        list?.let {
            mList = it
            notifyDataSetChanged()
        }
    }

}