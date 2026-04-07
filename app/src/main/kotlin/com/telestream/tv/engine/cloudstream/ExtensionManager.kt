package com.telestream.tv.engine.cloudstream

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

data class Repository(
    val name: String,
    val url: String,
    val description: String? = null
)

class ExtensionManager(private val client: OkHttpClient) {
    private val repositories = mutableListOf<Repository>()
    private val loadedExtensions = mutableListOf<MainAPI>()
    private val gson = Gson()

    fun addRepository(url: String) {
        // Fetch and load repository JSON
    }

    suspend fun loadExtensionsFromRepo(repo: Repository): List<ExtensionMetadata> {
        val request = Request.Builder().url(repo.url).build()
        return try {
            client.newCall(request).execute().use { response ->
                val body = response.body?.string() ?: return emptyList()
                gson.fromJson(body, Array<ExtensionMetadata>::class.java).toList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getLoadedExtensions(): List<MainAPI> = loadedExtensions
}

data class ExtensionMetadata(
    val name: String,
    val pkgName: String,
    val version: String,
    val author: String,
    val iconUrl: String?
)
