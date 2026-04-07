package com.telestream.tv.engine.metadata

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("search/multi")
    suspend fun searchMetadata(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): TMDBSearchResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): TMDBMovieDetail
}

data class TMDBSearchResponse(
    val results: List<TMDBResult>
)

data class TMDBResult(
    val id: Int,
    val media_type: String,
    val title: String?,
    val name: String?,
    val poster_path: String?,
    val vote_average: Double,
    val overview: String?
)

data class TMDBMovieDetail(
    val genres: List<Genre>,
    val runtime: Int?,
    val release_date: String?
)

data class Genre(val name: String)

class TMDBEngine(private val apiKey: String) {
    private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    fun getPosterUrl(path: String?): String? {
        return path?.let { "$IMAGE_BASE_URL$it" }
    }
}
