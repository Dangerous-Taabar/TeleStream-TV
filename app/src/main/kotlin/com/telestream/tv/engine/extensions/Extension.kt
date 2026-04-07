package com.telestream.tv.engine.extensions

data class SearchResponse(
    val title: String,
    val url: String,
    val posterUrl: String?,
    val source: String
)

interface MediaExtension {
    suspend fun search(query: String): List<SearchResponse>
    suspend fun getStreamUrls(url: String): List<String>
}

data class ExtensionMetadata(
    val name: String,
    val pkgName: String,
    val version: String,
    val repoUrl: String
)
