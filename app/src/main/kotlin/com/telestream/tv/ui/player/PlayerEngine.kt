package com.telestream.tv.ui.player

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

class PlayerEngine(private val context: Context) {
    private var player: ExoPlayer? = null

    fun initializePlayer(): ExoPlayer {
        // Optimize for Fast Zapping: Reduced buffer times for Live TV
        val loadControl = com.google.android.exoplayer2.DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                500,  // Min buffer
                2000, // Max buffer
                250,  // Buffer for playback
                500   // Buffer for re-playback
            )
            .build()

        player = ExoPlayer.Builder(context)
            .setLoadControl(loadControl)
            .build()
        return player!!
    }

    fun playStream(url: String, isLive: Boolean = true) {
        val dataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaSource = HlsMediaSource.Factory(dataSourceFactory)
            .setAllowChunklessPreparation(true) // Fast zapping optimization
            .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
            
        player?.apply {
            stop()
            setMediaSource(mediaSource)
            prepare()
            playWhenReady = true
        }
    }

    fun getAudioTracks() = player?.currentTracks?.groups?.filter { it.type == com.google.android.exoplayer2.C.TRACK_TYPE_AUDIO }
    fun getSubtitleTracks() = player?.currentTracks?.groups?.filter { it.type == com.google.android.exoplayer2.C.TRACK_TYPE_TEXT }
