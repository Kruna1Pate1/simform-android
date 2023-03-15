package com.krunal.demo.kotlinpractice.oop.`interface`.functional

fun interface KModifier<T> {
    operator fun invoke(value: T): T
}
