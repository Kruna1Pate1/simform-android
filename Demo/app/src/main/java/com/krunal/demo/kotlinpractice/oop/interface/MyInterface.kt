package com.krunal.demo.kotlinpractice.oop.`interface`

interface UserRepository {

    val source: String

    fun getUser(): User
    fun getAllUsers(): List<User>
}
