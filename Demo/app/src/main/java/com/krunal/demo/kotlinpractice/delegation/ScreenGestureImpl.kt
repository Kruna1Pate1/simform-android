package com.krunal.demo.kotlinpractice.delegation

class ScreenGestureImpl : ScreenGesture {

    private var brightness = 0
    private var volume = 0

    override fun leftScreenSwipe(distance: Int) {
        if (distance > 0) {
            println("Increasing brightness...")
        } else {
            println("Decreasing brightness...")
        }
        brightness += distance
        println("\uD83D\uDD06: $brightness")
    }

    override fun rightScreenSwipe(distance: Int) {
        if (distance > 0) {
            println("Increasing volume...")
        } else {
            println("Decreasing volume...")
        }
        volume += distance
        println("\uD83D\uDD0A: $volume")
    }
}
