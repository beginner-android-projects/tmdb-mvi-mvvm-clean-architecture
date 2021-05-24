package com.pondoo.kotlinwork.data

import com.pondoo.kotlinwork.data.local.ShowsDao
import com.pondoo.kotlinwork.data.model.*
import com.pondoo.kotlinwork.data.remote.TVShowsRemoteDataSources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val movieRemoteDataSource: TVShowsRemoteDataSources,
    private val showsDao: ShowsDao
) {

    suspend fun fetchTrendTvShows(
        category: String,
        apiKey: String,
        language: String,
        page: Int
    ): Flow<ResultState<Data<TvSeriesResponse>?>> {
        return flow {
            try {
               val x= movieRemoteDataSource.fetchTrendingTvShows(category, apiKey, language, page)
                emit(x)
            }catch (e:Exception){
                e.printStackTrace()
            }
            //Cache to database if response is successful
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchShowDetails(showID:String,apiKey: String,language: String): Flow<ResultState<ShowDetails>> {
        return flow {
            try {
                val x=movieRemoteDataSource.fetchShowDetail(showID, apiKey, language)
                emit(x)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
    }


    fun fetchFavShowsCached(): List<FavTvShowEntity>? =
           showsDao.getAll()?.let {
                it
            }
    fun insertFav(tvShow: FavTvShowEntity){
        showsDao.insertShow(tvShow)
    }
    fun deleteInFav(tvShow: FavTvShowEntity){
        showsDao.delete(tvShow)
    }
}
