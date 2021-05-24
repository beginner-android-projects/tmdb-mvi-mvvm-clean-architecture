package com.pondoo.kotlinwork.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pondoo.kotlinwork.data.model.FavTvShowEntity

@Database(entities = [FavTvShowEntity::class], version = 1)
abstract class DBFavTvs : RoomDatabase() {
    abstract fun showsDao(): ShowsDao
}