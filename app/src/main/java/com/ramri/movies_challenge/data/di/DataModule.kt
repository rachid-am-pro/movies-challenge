package com.ramri.movies_challenge.data.di

import com.google.samples.apps.nowinandroid.core.network.retrofit.RetrofitMoviesNetwork
import com.ramri.movies_challenge.data.repository.MoviesRepository
import com.ramri.movies_challenge.data.repository.OfflineFirstMoviesRepository
import com.ramri.movies_challenge.data.source.network.MoviesNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsMovieRepository(
        moviesRepository: OfflineFirstMoviesRepository,
    ): MoviesRepository

    @Binds
    internal abstract fun binds(
        impl: RetrofitMoviesNetwork
    ): MoviesNetworkDataSource
}
