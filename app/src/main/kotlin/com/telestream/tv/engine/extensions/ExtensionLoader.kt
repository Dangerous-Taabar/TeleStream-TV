package com.telestream.tv.engine.extensions

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class ExtensionLoader(private val client: OkHttpClient) {
    private val gson = Gson()

    suspend fun fetchRepo(url: String): List<ExtensionMetadata> {
        val request = Request.Builder()
            .url(url)
            .build()
        
        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return emptyList()
                val body = response.body?.string() ?: return emptyList()
                gson.fromJson(body, Array<ExtensionMetadata>::class.java).toList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
