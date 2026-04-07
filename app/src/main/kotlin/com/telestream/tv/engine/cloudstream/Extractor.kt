package com.telestream.tv.engine.cloudstream

interface Extractor {
    val name: String
    val mainUrl: String
    
    suspend fun getUrl(url: String, referer: String? = null): List<VideoLink>
}

data class VideoLink(
    val url: String,
    val name: String,
    val isM3u8: Boolean,
    val quality: Int = 0 // 1080, 720, etc.
)

class ExtractorLoader {
    private val extractors = mutableMapOf<String, Extractor>()
    
    fun registerExtractor(extractor: Extractor) {
        extractors[extractor.name] = extractor
    }
    
    suspend fun extract(url: String): List<VideoLink> {
        // Logic to find matching extractor and get URLs
        return emptyList()
    }
}
