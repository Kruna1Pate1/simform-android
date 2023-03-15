package com.krunal.demo.kotlinpractice.delegation

class SongDetails(map: Map<String, Any>) {

    val title: String by map
    val singer: String by map
    val music: String by map
    val releasedYear: Int by map
    val indieMusic: Boolean by map
}
