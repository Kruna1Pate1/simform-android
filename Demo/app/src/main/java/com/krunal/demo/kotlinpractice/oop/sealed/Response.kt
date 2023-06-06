package com.krunal.demo.kotlinpractice.oop.sealed

sealed interface Response {

    class Success<T : Any>(val data: T) : Response
    object Loading : Response
    data class Downloading(val read: Double, val total: Double) : Response {
        fun pause() {
            println("Download paused")
        }

        fun restart() {
            println("Download restart")
        }
    }

    sealed class Error(open val message: String?) : Response
    data class InternalError(override val message: String?) : Error(message)
    data class ServerError(val code: Int, override val message: String?) : Error(message)
}
