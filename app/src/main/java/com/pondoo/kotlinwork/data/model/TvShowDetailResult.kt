package com.pondoo.kotlinwork.data.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowDetails(
        @Json(name="genres")
    val genres: List<Genre>?,
        @Json(name="id")
    val id: Int?,
        @Json(name="name")
    val name: String?,
        @Json(name="number_of_episodes")
    val numberOfEpisodes: Int?,
        @Json(name="number_of_seasons")
    val numberOfSeasons: Int?,
        @Json(name="overview")
    val overview: String?,
        @Json(name="poster_path")
    val posterPath: String?,
        @Json(name="vote_average")
    val voteAverage: Double?,
        @Json(name="vote_count")
    val voteCount: Int?
)
@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name="id")
    val id: Int?,
    @Json(name="name")
    val name: String?
)