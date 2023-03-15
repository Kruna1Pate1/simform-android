package com.krunal.demo.kotlinpractice.errorhandling

class UserNotFoundException(override val message: String?) : Exception(message) {
    constructor() : this(null)
}
