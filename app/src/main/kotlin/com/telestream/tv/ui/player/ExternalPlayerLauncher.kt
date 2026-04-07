package com.telestream.tv.ui.player

import android.content.Context
import android.content.Intent
import android.net.Uri

class ExternalPlayerLauncher(private val context: Context) {
    fun launchVLC(url: String, title: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "video/*")
        intent.setPackage("org.videolan.vlc")
        intent.putExtra("title", title)
        intent.putExtra("from_start", true)
        
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            // Handle VLC not installed
            val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=org.videolan.vlc"))
            context.startActivity(playStoreIntent)
        }
    }
}
