package com.krunal.demo.kotlinpractice.visibilitymod

class Unrelated(outer: Outer) {

    init {
//        outer.a Cannot access 'a': it is private in 'Outer'
//        outer.b Cannot access 'b': it is protected in 'Outer'
        outer.c
        outer.d
//        outer.Nested Classifier 'Nested' does not have a companion object, and thus must be initialized here
    }
}
