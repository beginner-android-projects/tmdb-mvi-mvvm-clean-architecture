package com.pondoo.kotlinwork.data.local

import androidx.room.*
import com.pondoo.kotlinwork.data.model.FavTvShowEntity

@Dao
interface ShowsDao {

    @Query("SELECT * FROM fav_shows order by id DESC")
    fun getAll(): List<FavTvShowEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShow(show: FavTvShowEntity)

    @Delete
    fun delete(show: FavTvShowEntity)

    @Delete
    fun deleteAll(shows: List<FavTvShowEntity>)
}