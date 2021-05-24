package com.pondoo.kotlinwork.domain.mapper

import com.pondoo.kotlinwork.domain.model.TvShows
import com.pondoo.kotlinwork.data.model.Data
import com.pondoo.kotlinwork.data.model.TvSeriesResponse
import javax.inject.Inject

class TvShowListMapper @Inject constructor(): Mapper<Data<TvSeriesResponse>?, List<TvShows>?>{
    override fun mapFrom(response: Data<TvSeriesResponse>?): List<TvShows>? {
        return response!!.results?.map { tvSeriesResponse ->
            TvShows(
                    id = tvSeriesResponse.id ?: 0,
                    name = tvSeriesResponse.name.orEmpty(),
                    posterPath = tvSeriesResponse.posterPath.orEmpty(),
                    voteAverage = tvSeriesResponse.voteAverage ?: 0.0
            )
        } ?: listOf()
    }
}

