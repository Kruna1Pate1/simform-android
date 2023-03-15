package com.krunal.demo.kotlinpractice.oop.properties

class Square : Rectangle(), Polygon {

    // The compiler requires draw() to be overridden:
    override fun draw() {
        println("Square draw")
        super<Rectangle>.draw() // call to Rectangle.draw()
        super<Polygon>.draw() // call to Polygon.draw()
    }
}
