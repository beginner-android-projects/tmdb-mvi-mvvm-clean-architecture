package com.pondoo.kotlinwork.domain.mapper

import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import com.pondoo.kotlinwork.data.model.FavTvShowEntity
import javax.inject.Inject

class FavTvShowItemMapper @Inject constructor(): Mapper<List<FavTvShowEntity>, List<FavTvShowItem>> {
    override fun mapFrom(response: List<FavTvShowEntity>): List<FavTvShowItem> {
        return  response.map { favShow ->
            FavTvShowItem(id = favShow.id,
                    title = favShow.title,
                    posterPath = favShow.posterPath,
                    averageRating = favShow.averageRating)


        }?: listOf()
    }

}