package com.pondoo.kotlinwork.ui.activity.fav

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import com.pondoo.kotlinwork.domain.usecase.DeleteFromFavUseCase
import com.pondoo.kotlinwork.domain.usecase.FetchFavShowsUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavsViewModel  @ViewModelInject constructor(val fetchFavShowsUseCase: FetchFavShowsUseCase,
                                        val deleteFromFavUseCase: DeleteFromFavUseCase): ViewModel() {
    var _favShows= MutableLiveData<List<FavTvShowItem>>()
    val favShows=_favShows

    fun deleteFromFavs(tvShow: FavTvShowItem){
        GlobalScope.launch {
            deleteFromFavUseCase.deleteWatchlistItem(tvShow)
            getFavoriteShows()
        }
    }
    fun getFavoriteShows(){
            GlobalScope.launch {
                _favShows.postValue( fetchFavShowsUseCase.fetchFavShows())
            }

    }

}
