package com.krunal.demo.kotlinpractice.errorhandling

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun main() {
    val token = try {
        LoginRepo.login("Kruna1Pate1", "Krunal@321").toString()
    } catch (e: UserNotFoundException) {
        println(e.message)
    } catch (e: IllegalArgumentException) {
        println(e.message)
    } catch (e: InvalidPasswordException) {
        println(e.message)
    } catch (e: Exception) {
        println("Unknown exception ${e.message}")
    }

    if (token is String) {
        println("unique id: $token")
    }

    // try-with resource
    try {
        val url = URL("https://jsonplaceholder.typicode.com/todos/1")
        val con = url.openConnection() as HttpURLConnection
        BufferedReader(InputStreamReader(con.inputStream)).use {
            it.readLines().forEach(::println)
        }
    } catch (e: Exception) {
        println(e.message)
    } finally {
        println("Reading completed")
    }
}
