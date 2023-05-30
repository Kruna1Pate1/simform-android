package com.krunal.demo.recyclerview.listeners

interface OnItemChangeListener {

    fun onValueRemove(position: Int, valuePosition: Int)

    fun onValueAdd(position: Int, value: Int)

    fun onValueChange(position: Int, valuePosition: Int, value: Int)
}