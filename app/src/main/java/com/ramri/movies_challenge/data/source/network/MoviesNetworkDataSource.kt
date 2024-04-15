package com.ramri.movies_challenge.data.source.network

import com.ramri.movies_challenge.data.source.network.model.NetworkMovie

interface MoviesNetworkDataSource {
    suspend fun getMovies(query: String?, page: Int?): List<NetworkMovie>
}
