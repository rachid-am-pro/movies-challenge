package com.google.samples.apps.nowinandroid.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ramri.movies_challenge.data.source.network.MoviesNetworkDataSource
import com.ramri.movies_challenge.data.source.network.model.NetworkMovie
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitMoviesNetworkApi {
    @GET(value = "search/tv")
    suspend fun getMovies(
        @Query("api_key") apiKey: String?,
        @Query("query") query: String?,
        @Query("page") page: Int?
    ): NetworkResponse<List<NetworkMovie>>
}

private const val MOVIES_BASE_URL = "https://api.themoviedb.org/3/"
private const val MOVIES_API_KEY = "8d6a13d4ff986513574e73180f498ed9"

@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

@Singleton
internal class RetrofitMoviesNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : MoviesNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(MOVIES_BASE_URL)
        .callFactory { okhttpCallFactory.get().newCall(it) }
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitMoviesNetworkApi::class.java)

    override suspend fun getMovies(query: String?, page: Int?): List<NetworkMovie> =
        networkApi.getMovies(apiKey = MOVIES_API_KEY, query = query, page = page).data
}
