package com.pondoo.kotlinwork.ui.activity.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import com.pondoo.kotlinwork.domain.model.TvShowDetail
import com.pondoo.kotlinwork.domain.usecase.*
import com.pondoo.kotlinwork.utils.Config
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class DetailsViewModel @ViewModelInject constructor(val fetchShowDetailUseCase: FetchShowDetailUseCase,
                                                    val fetchFavShowsUseCase: FetchFavShowsUseCase,
                                                    val deleteFavlistItemUseCase: DeleteFromFavUseCase,
                                                    val insertInFavUseCase: AddIntoFavUseCase):ViewModel() {
    var _detail = MutableLiveData<TvShowDetail>()
    val detail=_detail
    var _favShows= MutableLiveData<List<FavTvShowItem>>()
    val favShows=_favShows


    fun apiCall(showID: String) {
        var language = Locale.getDefault().language
        viewModelScope.launch {
            fetchShowDetailUseCase.fetchShowDetail(showID, Config.api, language).collect {resource ->
                when(resource.status){
                    com.pondoo.kotlinwork.data.model.ResultState.Status.SUCCESS->{
                        _detail.value= resource.data
                    }
                }
            }
        }
    }

    fun addInFavs(tvShow: FavTvShowItem) {
        GlobalScope.launch {
            insertInFavUseCase.addFavItem(tvShow)
        }
    }

    fun deleteFromFavs(tvShow: FavTvShowItem) {
        GlobalScope.launch {
            deleteFavlistItemUseCase.deleteWatchlistItem(tvShow)
        }
    }

    fun getFavoriteShows() {
        GlobalScope.launch {

            _favShows.postValue(fetchFavShowsUseCase.fetchFavShows())
        }
    }
}