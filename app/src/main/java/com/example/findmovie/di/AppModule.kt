package com.example.findmovie.di

import android.content.Context
import androidx.room.Room
import com.example.findmovie.local.MovieDao
import com.example.findmovie.local.MyRoomDatabase
import com.example.findmovie.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): MyRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MyRoomDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoinDao(db: MyRoomDatabase): MovieDao {
        return db.movieDao()
    }

    @Provides
    @Singleton
    fun provideRepository(db:MovieDao): Repository {
        return Repository(db)
    }

















}