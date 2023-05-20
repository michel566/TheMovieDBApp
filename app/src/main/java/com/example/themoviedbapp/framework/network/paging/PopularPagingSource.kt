package com.example.themoviedbapp.framework.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.RemoteDataSource
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.framework.network.response.DataWrapperResponse
import com.example.themoviedbapp.framework.network.response.toMovieDomain

class PopularPagingSource(
    private val dataSource: RemoteDataSource<DataWrapperResponse>
    ) : PagingSource<Int, MovieDomain>() {

    override fun getRefreshKey(state: PagingState<Int, MovieDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDomain> {
        return try {
            val nextPage = params.key ?: PAGE_INDEX
            val popularResponse = dataSource.fetchPopular(
                language = LANGUAGE, page = nextPage)
            LoadResult.Page(
                data = popularResponse.results.map { it.toMovieDomain() },
                prevKey = null,
                nextKey = if (popularResponse.page >= nextPage) nextPage + PAGE_INDEX else null
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val PAGE_INDEX = 1
        private const val LANGUAGE = "en-US"
    }
}