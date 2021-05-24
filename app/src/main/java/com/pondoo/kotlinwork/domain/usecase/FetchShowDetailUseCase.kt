package com.pondoo.kotlinwork.domain.usecase

import com.pondoo.kotlinwork.data.MainRepository
import com.pondoo.kotlinwork.domain.model.TvShowDetail
import com.pondoo.kotlinwork.data.model.ResultState
import com.pondoo.kotlinwork.data.model.ResultState.Companion.map
import com.pondoo.kotlinwork.domain.mapper.TvShowDetailMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchShowDetailUseCase @Inject constructor(
        private val repository: MainRepository,
        private val mapper: TvShowDetailMapper
) {
    suspend fun fetchShowDetail(showID:String,apiKey: String,language: String): Flow<ResultState<TvShowDetail>> {
        return repository.fetchShowDetails(showID,apiKey,language).map {resource ->
            resource.map { baseResponse ->
                mapper.mapFrom(baseResponse)
            }
        }
    }
}