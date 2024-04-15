package com.ramri.movies_challenge.data.di

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.util.DebugLogger
import com.google.samples.apps.nowinandroid.core.network.retrofit.RetrofitMoviesNetwork
import com.ramri.movies_challenge.data.repository.MoviesRepository
import com.ramri.movies_challenge.data.repository.OfflineFirstMoviesRepository
import com.ramri.movies_challenge.data.source.network.MoviesNetworkDataSource
import com.ramri.movies_challenge.ui.movies.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                },
        )
        .build()

    @Provides
    @Singleton
    fun imageLoader(
        okHttpCallFactory: dagger.Lazy<Call.Factory>,
        @ApplicationContext application: Context,
    ): ImageLoader = ImageLoader.Builder(application)
        .callFactory { okHttpCallFactory.get() }
        .components { add(SvgDecoder.Factory()) }
        .respectCacheHeaders(false)
        .apply {
            logger(DebugLogger())
        }
        .build()

    @Singleton
    @Provides
    fun provideMoviesViewModel(moviesRepository: MoviesRepository): MoviesViewModel
        = MoviesViewModel(moviesRepository)
}
