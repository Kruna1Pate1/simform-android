package com.krunal.demo.kotlinpractice.delegation

import kotlin.reflect.KProperty

class PlaybackSpeed {

    private var speed = "1.0x"

    operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        println("$thisRef: ${property.name} getValue()")
        return speed
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("$thisRef: ${property.name} setValue()")
        speed = value
    }
}
