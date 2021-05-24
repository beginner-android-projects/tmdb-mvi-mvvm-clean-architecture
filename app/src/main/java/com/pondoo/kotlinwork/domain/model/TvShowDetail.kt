package com.pondoo.kotlinwork.domain.model

data class TvShowDetail(val id: Int?,
                        val name:String?,
                        val numberOfEpisodes:Int?,
                        val numberOfSeasons:Int?,
                        val overview:String?,
                        val posterPath:String?,
                        val voteAverage:Double?,
                        val voteCount:Int?,
                        val genres:String) {
}