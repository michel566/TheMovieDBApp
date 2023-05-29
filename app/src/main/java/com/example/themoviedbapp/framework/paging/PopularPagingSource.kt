package com.example.themoviedbapp.framework.paging

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
            val position = params.key ?: PAGE_INDEX
            val popularResponse = dataSource.fetchPopular(page = position)

            LoadResult.Page(
                data = getData(popularResponse),
                prevKey = null,
                nextKey = if (popularResponse.page >= position) position + PAGE_INDEX else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun getData(rawData: DataWrapperResponse) =
        rawData.results.map {
            it.toMovieDomain()
        }

    companion object {
        private const val PAGE_INDEX = 1
    }
}