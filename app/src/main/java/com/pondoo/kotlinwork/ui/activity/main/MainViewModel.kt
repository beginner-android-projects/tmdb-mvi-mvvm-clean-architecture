package com.pondoo.kotlinwork.ui.activity.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pondoo.kotlinwork.data.model.ResultState
import com.pondoo.kotlinwork.domain.model.FavTvShowItem
import com.pondoo.kotlinwork.domain.model.TvShows
import com.pondoo.kotlinwork.domain.usecase.*
import com.pondoo.kotlinwork.utils.Config
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class MainViewModel  @ViewModelInject constructor(val fetchTvShowsUseCase: FetchTvShowsUseCase,
                                                  val fetchFavShowsUseCase: FetchFavShowsUseCase,
                                                  val deleteFavlistItemUseCase: DeleteFromFavUseCase,
                                                  val insertInFavUseCase: AddIntoFavUseCase): ViewModel() {

    var _data=MutableLiveData<List<TvShows>>()
    val data:LiveData<List<TvShows>> =  _data
    var _favShows= MutableLiveData<List<FavTvShowItem>>()
    val favShows=_favShows
    fun apiCall(i:Int){
        try {
            var language = Locale.getDefault().language //Getting device Language
            viewModelScope.launch {
                fetchTvShowsUseCase.fetchTrendShows(Config.category, Config.api, language, i).collect { resource ->
                    when(resource.status){
                        ResultState.Status.SUCCESS->{
                            _data.value = resource.data!!
                        }
                    }

                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun addInFavs(tvShow: FavTvShowItem){
        GlobalScope.launch {
            insertInFavUseCase.addFavItem(tvShow)
            getFavoriteShows()
        }
    }
    fun deleteFromFavs(tvShow: FavTvShowItem){
        GlobalScope.launch {
            deleteFavlistItemUseCase.deleteWatchlistItem(tvShow)
            getFavoriteShows()
        }
    }
    fun getFavoriteShows(){
        GlobalScope.launch {
            _favShows.postValue(fetchFavShowsUseCase.fetchFavShows())
        }
    }
}