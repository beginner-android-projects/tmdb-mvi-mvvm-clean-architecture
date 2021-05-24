package com.pondoo.kotlinwork.domain.mapper

import com.pondoo.kotlinwork.domain.model.TvShowDetail
import com.pondoo.kotlinwork.data.model.ShowDetails
import javax.inject.Inject

class TvShowDetailMapper @Inject constructor(): Mapper<ShowDetails, TvShowDetail>{
    override fun mapFrom(response: ShowDetails): TvShowDetail {
        var genres=""
        for(i in response.genres!!){
            if(!genres.equals("")){
                genres=genres+", "+i.name
            }else{
                genres=i.name!!
            }

        }
        return response.let {TvShowDetail(
                response.id,
                response.name,
                response.numberOfEpisodes,
                response.numberOfSeasons,
                response.overview,
                response.posterPath,
                response.voteAverage,
                response.voteCount,
                genres)
        }
    }

}