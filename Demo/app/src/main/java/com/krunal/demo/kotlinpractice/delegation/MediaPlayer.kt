package com.krunal.demo.kotlinpractice.delegation

import kotlin.properties.Delegates
import kotlin.random.Random

class MediaPlayer(gesture: ScreenGesture) : ScreenGesture by gesture {

    private var playbackSpeed: String by PlaybackSpeed()
    private val _songDetails = SongDetails(
        mapOf(
            "Title" to "Tum se hi...",
            "Singer" to "Mohit Chauhan",
            "Music" to "Pritham",
            "Released Year" to 2007,
            "Indie Music" to false
        )
    )
    val songDetails: String
        get() {
            return """
                    title: ${_songDetails.title}
                    singer: ${_songDetails.singer}
                    music: ${_songDetails.music}
                    releasedYear: ${_songDetails.releasedYear}
                    indieMusic: ${_songDetails.indieMusic}
        """.trimIndent()
        }
    val a: Lazy<Int> = lazy { 5 }
    val song: String by lazy {
        println("starting song...")
        "Tum se hi..."
    }
    var timeRemain: Int by Delegates.observable(100) { property, oldValue, newValue ->
        println("${property.name}: $oldValue -> $newValue")
    }

    fun swapLeft(up: Boolean = true) {
        a.value
        if (up) leftScreenSwipe(Random.nextInt(400))
        else leftScreenSwipe(-Random.nextInt(400))
    }

    fun swapRight(up: Boolean = true) {
        if (up) rightScreenSwipe(Random.nextInt(400))
        else rightScreenSwipe(-Random.nextInt(400))
    }

    fun changePlaybackSpeed(inc: Boolean) {
        playbackSpeed.removeSuffix("x").toDoubleOrNull()?.let {
            if (inc) playbackSpeed = "${it + 0.5}x"
            else playbackSpeed = "${it - 0.5}x"
        }
        println("new playback-speed is $playbackSpeed")
    }
}