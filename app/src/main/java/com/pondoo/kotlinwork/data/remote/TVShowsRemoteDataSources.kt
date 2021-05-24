package com.pondoo.kotlinwork.data.remote

import com.pondoo.kotlinwork.data.model.*
import com.pondoo.kotlinwork.network.ApiInterface
import com.pondoo.kotlinwork.utils.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject


class TVShowsRemoteDataSources @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchTrendingTvShows(category:String, apiKey:String, language:String, page: Int): ResultState<Data<TvSeriesResponse>> {
        val showService = retrofit.create(ApiInterface::class.java)
        return getResponse(
            request = { showService.getPopularTv(category,apiKey,language,page) },
            defaultErrorMessage = "Error fetching Movie list")

    }
    suspend fun fetchShowDetail(showID:String,apiKey: String,language: String): ResultState<ShowDetails> {
        val showService=retrofit.create(ApiInterface::class.java)
        return getResponse(request = { showService.getDetail(showID,apiKey,language) },
            defaultErrorMessage = "Error fetching Movie list")
    }
    private suspend fun  <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): ResultState<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return ResultState.success(result.body()!!)
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                return ResultState.error(errorResponse!!)
            }

        } catch (e: Throwable) {
            e.printStackTrace()
            return ResultState.error(Error(404, e.message.toString()))
        }
    }
}