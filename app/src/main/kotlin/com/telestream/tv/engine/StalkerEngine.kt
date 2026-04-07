package com.telestream.tv.engine

import com.telestream.tv.model.IPTVChannel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface StalkerService {
    @GET("server/load.php")
    suspend fun getChannels(
        @Header("Authorization") auth: String,
        @Query("type") type: String = "itv",
        @Query("action") action: String = "get_all_channels"
    ): StalkerChannelsResponse
}

data class StalkerChannelsResponse(
    val js: List<StalkerChannel>
)

data class StalkerChannel(
    val id: String,
    val name: String,
    val logo: String?,
    val cmd: String?
)

class StalkerEngine {
    fun convertToIPTVChannels(stalkerChannels: List<StalkerChannel>): List<IPTVChannel> {
        return stalkerChannels.map {
            IPTVChannel(
                id = it.id,
                name = it.name,
                url = it.cmd ?: "", // Usually a ffmpeg command or URL
                logo = it.logo
            )
        }
    }
}
