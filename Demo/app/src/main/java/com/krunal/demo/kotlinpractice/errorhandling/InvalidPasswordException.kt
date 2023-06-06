package com.krunal.demo.kotlinpractice.errorhandling

class InvalidPasswordException(override val message: String?) : Exception(message) {

    constructor() : this(null)
}
