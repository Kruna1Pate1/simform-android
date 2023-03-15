package com.krunal.demo.kotlinpractice.oop.classandobj

fun main() {
    val book1 = Book(
        name = "harry potter and philosopher's stone",
        author = "J.K. Rowling"
    )
    val book2 = Book(book1)

    Database.getInstance()
    val person = Person2(Person2.Name("Will", null, "byers"))
}

