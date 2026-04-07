package com.telestream.tv.model

enum class PortalType {
    M3U, XTREAM, STALKER, TELEGRAM
}

data class PortalSource(
    val id: String,
    val name: String,
    val type: PortalType,
    val url: String,
    val username: String? = null,
    val password: String? = null,
    val macAddress: String? = null
)

data class IPTVChannel(
    val id: String,
    val sourceId: String,
    val name: String,
    val url: String,
    val logo: String? = null,
    val category: String? = null,
    val epgId: String? = null,
    val isFavorite: Boolean = false
)

data class IPTVPlaylist(
    val source: PortalSource,
    val channels: List<IPTVChannel>
)
