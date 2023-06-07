package com.krunal.demo.kotlinpractice.scopped

import com.krunal.demo.kotlinpractice.oop.dataclass.UserDetails

fun main() {
    val user = UserDetails("Harry", 20, "Male")
    println(user)

    val let = user.let {
        println("let: $it")
        "return let"
    }
    println(let)

    val also = user.also { user ->
        println("also: $user")
        "return also"
    }
    println(also)

    val apply = user.apply {
        println("apply: $this")
        "return apply"
    }
    println(apply)

    val runEx = user.run {
        println("run: $this")
        "return run"
    }
    println(runEx)

    val run = run { "return run" }
    println(run)
    val with = with(user) {
        println("with: $this")
        "return with"
    }
    println(with)
}
