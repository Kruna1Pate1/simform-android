package com.krunal.demo.kotlinpractice.oop.classandobj

class Database private constructor() {

    companion object {

        private lateinit var instance: Database
        fun getInstance(): Database {
            if (!Companion::instance.isInitialized) {
                println("Creating new db obj")
                instance = Database()
            }
            return instance
        }
    }
}
