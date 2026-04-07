package com.telestream.tv.engine.iptv

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream

data class EpgProgram(
    val channelId: String,
    val start: String,
    val stop: String,
    val title: String,
    val description: String? = null
)

class XMLTVParser {
    fun parse(inputStream: InputStream): List<EpgProgram> {
        val programs = mutableListOf<EpgProgram>()
        val parser = Xml.newPullParser()
        parser.setInput(inputStream, null)

        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && parser.name == "programme") {
                val channelId = parser.getAttributeValue(null, "channel")
                val start = parser.getAttributeValue(null, "start")
                val stop = parser.getAttributeValue(null, "stop")
                
                var title = ""
                var desc = ""
                
                var innerEvent = parser.next()
                while (!(innerEvent == XmlPullParser.END_TAG && parser.name == "programme")) {
                    if (innerEvent == XmlPullParser.START_TAG) {
                        when (parser.name) {
                            "title" -> title = parser.nextText()
                            "desc" -> desc = parser.nextText()
                        }
                    }
                    innerEvent = parser.next()
                }
                
                programs.add(EpgProgram(channelId, start, stop, title, desc))
            }
            eventType = parser.next()
        }
        return programs
    }
}
