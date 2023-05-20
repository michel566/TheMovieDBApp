package com.example.core.usecase.popularusecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface GetPopularUseCase {
    operator fun invoke(params: GetPopularParams): Flow<PagingData<MovieDomain>>
    data class GetPopularParams(val pagingConfig: PagingConfig)
}