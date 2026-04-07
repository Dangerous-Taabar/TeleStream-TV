package com.telestream.tv.engine.iptv

import com.telestream.tv.model.IPTVChannel

class LiveTVSearchEngine(private val channels: List<IPTVChannel>) {
    fun search(query: String): List<IPTVChannel> {
        if (query.isBlank()) return emptyList()
        return channels.filter { 
            it.name.contains(query, ignoreCase = true) || 
            it.category?.contains(query, ignoreCase = true) == true
        }
    }
}
