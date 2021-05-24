package com.pondoo.kotlinwork.network

import com.pondoo.kotlinwork.data.model.Data
import com.pondoo.kotlinwork.data.model.ShowDetails
import com.pondoo.kotlinwork.data.model.TvSeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("3/tv/{category}")
    suspend fun getPopularTv(@Path("category") category:String,
                             @Query("api_key") apiKey:String,
                             @Query("language") language:String,
                             @Query("page") page: Int): Response<Data<TvSeriesResponse>>


    @GET("/3/tv/{tv_id}")
    suspend fun getDetail(@Path("tv_id")tvId:String,
                          @Query("api_key")apiKey: String,
                          @Query("language")language: String): Response<ShowDetails>
}