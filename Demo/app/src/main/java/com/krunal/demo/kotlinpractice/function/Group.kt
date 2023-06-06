package com.krunal.demo.kotlinpractice.function

class Group(
    private val persons: MutableList<String> = mutableListOf()
) {

    operator fun plus(other: Group) {
        persons.addAll(other.persons)
    }

    operator fun minus(other: Group) {
        persons.removeAll(other.persons)
    }

    operator fun dec(): Group {
        return Group(persons.subList(0, persons.lastIndex - 1))
    }

    operator fun unaryPlus() {
        persons.add("Dummy")
    }

    override fun equals(other: Any?): Boolean {
        return persons == (other as? Group)?.persons
    }

    override fun hashCode(): Int {
        return persons.hashCode()
    }
}
