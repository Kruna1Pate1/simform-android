package com.krunal.demo.recyclerview.listeners

interface OnItemChangeListener {

    fun onCalculationRemove(position: Int)

    fun onNumberChange(position: Int, num1: Double?, num2: Double?)

    fun onValueRemove(position: Int, valuePosition: Int)

    fun onValueAdd(position: Int, value: Int)

    fun onValueChange(position: Int, valuePosition: Int, value: Int)

    fun onImageRemove(position: Int, imagePosition: Int)

    fun onImageAdd(position: Int)
}