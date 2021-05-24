package com.pondoo.kotlinwork.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_shows")
class FavTvShowEntity (
    @NonNull
    @PrimaryKey
    val id: Int,
    val title: String?,
    val posterPath:String?,
    val averageRating:Double?
)