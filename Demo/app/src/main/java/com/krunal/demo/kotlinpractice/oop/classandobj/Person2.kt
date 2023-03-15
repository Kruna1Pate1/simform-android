package com.krunal.demo.kotlinpractice.oop.classandobj

class Person2(val name: Name) {

    var age: Int = 20

    class Name(
        var fname: String, var mname: String?, var lname: String
    ) {

        init {
//            println("age: $age")
        }
    }

    inner class Address(street: String) {

        init {
            println("${name.fname} lives in $street")
        }
    }
}
