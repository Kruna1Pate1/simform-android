package com.krunal.demo.kotlinpractice.function

fun main() {
    fun calculate(n1: Int, n2: Int, cal: (Int, Int) -> Int) {
        println("$n1, $n2 = ${cal(n1, n2)}")
    }

    val add = { a: Int, b: Int -> a + b }
    calculate(5, 10, cal = add)
    calculate(5, 10) { a, b -> a * b }

    // Anonymous function
    calculate(50, 10, fun(a: Int, b) = a / b)

    // lambda as class extension
    val repeat: String.(Int) -> String = {
        var str = this
        repeat(it - 1) { str += " $this" }
        str
    }
    println("Hello".repeat(5))

    // Java Functional interface in lambda
    fun transformer(value: String, mod: Modifier<String>) {
        println(mod(value))
    }

    val abc = Modifier<String> {
        return@Modifier ""
    }
    val modifier = { value: String -> value.uppercase() }
    fun modifierFun(value: String) = value.uppercase()
    transformer(value = "hello", mod = modifier)
    transformer(value = "hello", mod = ::modifierFun)
    transformer(value = "world") {
        it.uppercase()
    }
}
