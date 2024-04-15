package com.ramri.movies_challenge.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ramri.movies_challenge.data.model.Movie

@Entity(
    tableName = "movies",
)
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val adult: Boolean?,
    val backdropPath: String?,
    val budget: Int?,
    val homepage: String?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)

fun MovieEntity.asExternalModel() = Movie(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    budget = budget,
    homepage = homepage,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    status = status,
    tagline = status,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)
