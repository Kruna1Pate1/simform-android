package com.krunal.demo.kotlinpractice.delegation

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    val player: MediaPlayer = MediaPlayer(ScreenGestureImpl())

    player.swapLeft()
    player.swapLeft(false)
    player.swapRight(true)
    player.swapRight(false)

    // property delegate
    player.changePlaybackSpeed(true)
    player.changePlaybackSpeed(false)

    // lazy
    println("Current song: ${player.song}")
    println("Current song: ${player.song}")
    println("Current song: ${player.song}")

    // Observable
    runBlocking {
        repeat(5) {
            player.timeRemain -= 20
            delay(500)
        }
    }

    // Map
    println(player.songDetails)
}
