package com.pondoo.kotlinwork.domain.usecase

import com.pondoo.kotlinwork.data.MainRepository
import com.pondoo.kotlinwork.domain.mapper.FavTvShowMapper
import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import javax.inject.Inject

class  DeleteFromFavUseCase @Inject constructor(
        private val repository: MainRepository,
        private val mapper: FavTvShowMapper
) {
    fun deleteWatchlistItem(favTvShowItem: FavTvShowItem) {
        return repository.deleteInFav(mapper.mapFrom(favTvShowItem))
    }
}