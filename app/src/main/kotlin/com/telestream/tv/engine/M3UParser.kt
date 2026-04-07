package com.telestream.tv.engine

import com.telestream.tv.model.IPTVChannel
import java.io.BufferedReader
import java.io.StringReader

class M3UParser {
    fun parse(m3uContent: String): List<IPTVChannel> {
        val channels = mutableListOf<IPTVChannel>()
        val reader = BufferedReader(StringReader(m3uContent))
        var line: String? = reader.readLine()
        
        var currentName = ""
        var currentLogo = ""
        var currentCategory = ""
        var currentEpgId = ""

        while (line != null) {
            if (line.startsWith("#EXTINF:")) {
                // Parse EXTINF metadata
                currentName = line.substringAfterLast(",").trim()
                currentLogo = parseMetadata(line, "tvg-logo")
                currentCategory = parseMetadata(line, "group-title")
                currentEpgId = parseMetadata(line, "tvg-id")
            } else if (line.startsWith("http") || line.startsWith("rtmp")) {
                // This is the URL line
                channels.add(IPTVChannel(
                    id = java.util.UUID.randomUUID().toString(),
                    name = currentName,
                    url = line.trim(),
                    logo = currentLogo.ifEmpty { null },
                    category = currentCategory.ifEmpty { "General" },
                    epgId = currentEpgId.ifEmpty { null }
                ))
                // Reset metadata for next channel
                currentName = ""
                currentLogo = ""
                currentCategory = ""
                currentEpgId = ""
            }
            line = reader.readLine()
        }
        return channels
    }

    private fun parseMetadata(line: String, key: String): String {
        val prefix = "$key=\""
        if (!line.contains(prefix)) return ""
        val start = line.indexOf(prefix) + prefix.length
        val end = line.indexOf("\"", start)
        return if (end > start) line.substring(start, end) else ""
    }
}
