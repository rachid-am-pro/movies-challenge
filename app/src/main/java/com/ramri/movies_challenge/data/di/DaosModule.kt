package com.ramri.movies_challenge.data.di

import com.ramri.movies_challenge.data.source.local.MoviesDatabase
import com.ramri.movies_challenge.data.source.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesMoviesDao(
        database: MoviesDatabase,
    ): MovieDao = database.movieDao()
}
