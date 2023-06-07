package com.krunal.demo.kotlinpractice.delegation

class PlayerConfig(val map: MutableMap<String, Any?>) {

    var brightness: Int by map
    var volume: Int by map
}
