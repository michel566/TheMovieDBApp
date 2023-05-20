package com.example.core.data

import androidx.paging.PagingSource
import com.example.core.model.MovieDomain

interface PopularRepository {
    fun fetchPopular(): PagingSource<Int, MovieDomain>
}