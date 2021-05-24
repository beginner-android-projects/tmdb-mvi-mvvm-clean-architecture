package com.pondoo.kotlinwork.domain.usecase

import com.pondoo.kotlinwork.data.MainRepository
import com.pondoo.kotlinwork.domain.model.TvShows
import kotlinx.coroutines.flow.Flow
import com.pondoo.kotlinwork.data.model.ResultState
import com.pondoo.kotlinwork.data.model.ResultState.Companion.map
import com.pondoo.kotlinwork.domain.mapper.TvShowListMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchTvShowsUseCase @Inject constructor(
        private val repository: MainRepository,
        private val mapper: TvShowListMapper) {
    suspend fun fetchTrendShows(category:String,
                                apiKey: String,
                                language: String,
                                page: Int): Flow<ResultState<List<TvShows>>> {
        return repository.fetchTrendTvShows(category,apiKey,language,page)
                .map { resource ->
                        resource.map { baseResponse ->
                            mapper.mapFrom(baseResponse)!!
                        }

                }
    }
}
