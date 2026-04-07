package com.telestream.tv.engine.cloudstream

class VODSearchEngine(private val providers: List<MainAPI>) {
    suspend fun search(query: String): List<SearchResponse> {
        val allResults = mutableListOf<SearchResponse>()
        // Perform search across all registered providers
        providers.forEach { provider ->
            try {
                val results = provider.search(query)
                allResults.addAll(results)
            } catch (e: Exception) {
                // Log and continue
            }
        }
        return allResults
    }
}
