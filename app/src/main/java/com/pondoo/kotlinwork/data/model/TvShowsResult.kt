package com.pondoo.kotlinwork.data.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data<T>(
    @Json(name="results")
    val results: List<T>?
)
@JsonClass(generateAdapter = true)
data class TvSeriesResponse(
    @Json(name="id")
    val id: Int?,
    @Json(name="name")
    val name: String?,
    @Json(name="poster_path")
    val posterPath: String?,
    @Json(name="vote_average")
    val voteAverage: Double?
)