package com.telestream.tv.engine.cloudstream

enum class TvType {
    Movie, TvSeries, Anime, LiveStream
}

abstract class MainAPI {
    abstract val name: String
    abstract val mainUrl: String
    abstract val supportedTypes: Set<TvType>

    abstract suspend fun search(query: String): List<SearchResponse>
    abstract suspend fun load(url: String): LoadResponse
    abstract suspend fun loadLinks(data: String, isCasting: Boolean, callback: (String) -> Unit): Boolean
}

data class SearchResponse(
    val name: String,
    val url: String,
    val posterUrl: String?,
    val type: TvType,
    val apiName: String
)

data class LoadResponse(
    val name: String,
    val url: String,
    val type: TvType,
    val posterUrl: String?,
    val description: String?,
    val dataText: String? = null // For storing raw data like episode list or playlist
)
