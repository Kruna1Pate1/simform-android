package com.krunal.demo.kotlinpractice.oop.classandobj

class Book(
    var name: String,
    var author: String?,
) {

    var rating: Int = 0

    constructor(book: Book) : this(book.name, book.author) {
        rating += 10
        println("in constructor")
    }

    init {
        println("in init1")
    }

    init {
        println("in init2")
    }

    companion object {
        init {
            println("static init")
        }

        var a = 0
    }
}