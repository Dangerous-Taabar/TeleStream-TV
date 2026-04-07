package com.telestream.tv.engine

import com.telestream.tv.model.PortalSource
import com.telestream.tv.model.PortalType

class PortalManager {
    private val sources = mutableListOf<PortalSource>()

    fun addSource(source: PortalSource) {
        sources.add(source)
    }

    fun removeSource(id: String) {
        sources.removeIf { it.id == id }
    }

    fun getAllSources(): List<PortalSource> = sources

    fun getSourcesByType(type: PortalType): List<PortalSource> {
        return sources.filter { it.type == type }
    }
    
    // In a real app, this would use Room/SharedPreferences to persist
    fun saveToPersistence() {
        // Logic to save to local database
    }
}
