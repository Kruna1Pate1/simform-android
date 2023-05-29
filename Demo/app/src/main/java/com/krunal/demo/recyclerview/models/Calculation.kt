package com.krunal.demo.recyclerview.models

data class Calculation(
    val num1: Double,
    val num2: Double,
    val additionalNums: List<Int> = emptyList(),
    val images: List<Int> = emptyList()
) {

    val total: Double
        get() = num1 * num2 + additionalNums.sum()

    companion object {
        val dummyData: List<Calculation> = listOf(
            Calculation(
                50.0, 90.0, listOf(1)
            )
        )
    }
}