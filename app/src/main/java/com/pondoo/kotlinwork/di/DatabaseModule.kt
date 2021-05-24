package com.pondoo.kotlinwork.di

import android.content.Context
import androidx.room.Room
import com.pondoo.kotlinwork.data.local.DBFavTvs
import com.pondoo.kotlinwork.data.local.ShowsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): DBFavTvs {
        return Room.databaseBuilder(
                appContext,
                DBFavTvs::class.java,
                "app.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(appDatabase: DBFavTvs): ShowsDao {
        return appDatabase.showsDao()
    }
}