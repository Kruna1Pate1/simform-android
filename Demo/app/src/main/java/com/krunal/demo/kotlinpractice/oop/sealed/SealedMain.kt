package com.krunal.demo.kotlinpractice.oop.sealed

fun main() {
    val response: Response = Response.Success("Welcome to the world!!")
    response.printResponse()

    Response.Loading.printResponse()
    Response.Downloading(45.0, 100.0).let {
        it.printResponse()
        it.pause()
        it.restart()
    }
    Response.InternalError("no internet connection").printResponse()
    Response.ServerError(401, "unauthorized access").printResponse()
//    Response.Error() sealed class can't be initialized
}

fun Response.printResponse() {
    when (this) {
        is Response.Success<*> -> println("Successful: $data")
        Response.Loading -> println("Loading data")
        is Response.Downloading -> println("Download: ${(read / total) * 100}%")
        is Response.InternalError -> println("Internal error $message")
        is Response.ServerError -> println("$code $message")
    }
}
