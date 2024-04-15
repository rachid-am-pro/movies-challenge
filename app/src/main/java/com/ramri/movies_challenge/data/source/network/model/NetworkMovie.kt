package com.ramri.movies_challenge.data.source.network.model

import com.ramri.movies_challenge.data.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovie(
    @SerialName("id") val id: String,
    @SerialName("adult") val adult: Boolean? = false,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("budget") val budget: Int? = null,
    @SerialName("homepage") val homepage: String? = null,
    @SerialName("imdb_id") val imdbId: String? = null,
    @SerialName("original_language") val originalLanguage: String? = null,
    @SerialName("original_title") val originalTitle: String? = null,
    @SerialName("overview") val overview: String? = null,
    @SerialName("popularity") val popularity: Double? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("revenue") val revenue: Int? = null,
    @SerialName("runtime") val runtime: Int? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("tagline") val tagline: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("video") val video: Boolean? = false,
    @SerialName("vote_average") val voteAverage: Double? = null,
    @SerialName("vote_count") val voteCount: Int? = null,
    var isFavorite: Boolean? = false
)

fun NetworkMovie.asExternalModel() = Movie(
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