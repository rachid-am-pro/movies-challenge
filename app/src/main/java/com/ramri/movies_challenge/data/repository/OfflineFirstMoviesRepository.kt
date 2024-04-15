package com.ramri.movies_challenge.data.repository

import com.ramri.movies_challenge.data.model.Movie
import com.ramri.movies_challenge.data.source.local.dao.MovieDao
import com.ramri.movies_challenge.data.source.local.entity.MovieEntity
import com.ramri.movies_challenge.data.source.local.entity.asExternalModel
import com.ramri.movies_challenge.data.source.network.MoviesNetworkDataSource
import com.ramri.movies_challenge.data.source.network.model.NetworkMovie
import com.ramri.movies_challenge.data.source.network.model.asExternalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

internal class OfflineFirstMoviesRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val network: MoviesNetworkDataSource,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : MoviesRepository {
    override suspend fun getMovies(): List<Movie> = withContext(ioDispatcher) {
        network.getMovies("comedy", 1).map { it.asExternalModel() }
    }

    override fun getMovie(id: String): Flow<Movie> =
        movieDao.getMovieEntity(id).map { it.asExternalModel() }
}
