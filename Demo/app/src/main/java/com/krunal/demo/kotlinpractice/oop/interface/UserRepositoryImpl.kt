package com.krunal.demo.kotlinpractice.oop.`interface`

class UserRepositoryImpl : UserRepository {

    private val users = mutableListOf(
        User("Kruna1Pate1", "Krunal@123"),
        User("Harsh", "Harsh@123"),
        User("Ankur", "Ankur#123")
    )

    override var source: String = "Local database"

    override fun getUser(): User {
        return users.random()
    }

    override fun getAllUsers(): List<User> = users
}
