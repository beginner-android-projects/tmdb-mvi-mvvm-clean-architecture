package com.pondoo.kotlinwork.domain.mapper

import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import com.pondoo.kotlinwork.data.model.FavTvShowEntity
import javax.inject.Inject

class FavTvShowMapper  @Inject constructor() :
        Mapper<FavTvShowItem, FavTvShowEntity> {
    override fun mapFrom(response: FavTvShowItem): FavTvShowEntity {
        return FavTvShowEntity(
                id = response.id,
                title = response.title,
                posterPath = response.posterPath,
                averageRating = response.averageRating

        )
    }
}