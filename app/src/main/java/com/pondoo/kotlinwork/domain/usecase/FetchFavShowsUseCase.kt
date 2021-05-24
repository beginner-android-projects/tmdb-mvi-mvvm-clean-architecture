package com.pondoo.kotlinwork.domain.usecase

import com.pondoo.kotlinwork.data.MainRepository
import com.pondoo.kotlinwork.domain.mapper.FavTvShowItemMapper
import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import javax.inject.Inject

class FetchFavShowsUseCase @Inject constructor(
        private val repository: MainRepository,
        private val mapper: FavTvShowItemMapper) {

    suspend fun fetchFavShows(): List<FavTvShowItem>{
        return mapper.mapFrom(repository.fetchFavShowsCached()!!)
    }
}
