package com.ramri.movies_challenge.data.repository

import com.ramri.movies_challenge.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>

    fun getMovie(id: String): Flow<Movie>
}
