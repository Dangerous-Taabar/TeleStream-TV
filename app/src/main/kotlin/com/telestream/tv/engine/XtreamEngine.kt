package com.telestream.tv.engine

import com.telestream.tv.model.IPTVChannel
import retrofit2.http.GET
import retrofit2.http.Query

interface XtreamService {
    @GET("player_api.php")
    suspend fun login(
        @Query("username") user: String,
        @Query("password") pass: String
    ): XtreamResponse

    @GET("player_api.php")
    suspend fun getLiveStreams(
        @Query("action") action: String = "get_live_streams"
    ): List<XtreamChannel>
}

data class XtreamResponse(
    val user_info: UserInfo,
    val server_info: ServerInfo
)

data class UserInfo(val username: String, val status: String)
data class ServerInfo(val url: String, val port: String)

data class XtreamChannel(
    val stream_id: String,
    val name: String,
    val stream_icon: String?,
    val category_id: String?
)

class XtreamEngine {
    fun convertToIPTVChannels(xtreamChannels: List<XtreamChannel>, baseUrl: String): List<IPTVChannel> {
        return xtreamChannels.map {
            IPTVChannel(
                id = it.stream_id,
                sourceId = "XTREAM",
                name = it.name,
                url = "$baseUrl/live/${it.stream_id}.ts", // Standard Xtream URL format
                logo = it.stream_icon,
                category = it.category_id
            )
        }
    }
}
