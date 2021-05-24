package com.pondoo.kotlinwork.domain.usecase

import com.pondoo.kotlinwork.data.MainRepository
import com.pondoo.kotlinwork.domain.mapper.FavTvShowMapper
import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import javax.inject.Inject

class AddIntoFavUseCase @Inject constructor(private val repository: MainRepository, private val mapper: FavTvShowMapper
) {
    fun addFavItem(favTvShowItem: FavTvShowItem) {
        return repository.insertFav(mapper.mapFrom(favTvShowItem))
    }
}