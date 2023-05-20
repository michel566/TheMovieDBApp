package com.example.core.usecase.popularusecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.core.data.PopularRepository
import com.example.core.model.MovieDomain
import com.example.core.usecase.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularUseCaseImpl @Inject constructor(
    private val repository: PopularRepository
) : PagingUseCase<GetPopularUseCase.GetPopularParams, MovieDomain>(), GetPopularUseCase {

    override fun createFlowObservable(params: GetPopularUseCase.GetPopularParams):
            Flow<PagingData<MovieDomain>> {
        val pagingSource = repository.fetchPopular()
        return Pager(config = params.pagingConfig) {
            repository.fetchPopular()
        }.flow
    }
}