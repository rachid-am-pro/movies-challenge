package com.ramri.movies_challenge.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.ramri.movies_challenge.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query(
        value = """
        SELECT * FROM movies
        WHERE id = :movieId
    """,
    )
    fun getMovieEntity(movieId: String): Flow<MovieEntity>

    @Query(value = "SELECT * FROM movies")
    fun getMovieEntities(): Flow<List<MovieEntity>>

    @Query(value = "SELECT * FROM movies")
    suspend fun getOneOffMovieEntities(): List<MovieEntity>

    @Query(
        value = """
        SELECT * FROM movies
        WHERE id IN (:ids)
    """,
    )
    fun getMovieEntities(ids: Set<String>): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreMovies(movieEntities: List<MovieEntity>): List<Long>

    @Upsert
    suspend fun upsertMovies(entities: List<MovieEntity>)

    @Query(
        value = """
            DELETE FROM movies
            WHERE id in (:ids)
        """,
    )
    suspend fun deleteMovies(ids: List<String>)
}
