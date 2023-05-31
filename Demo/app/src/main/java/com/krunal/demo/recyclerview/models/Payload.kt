package com.krunal.demo.recyclerview.models

import android.net.Uri

enum class PayloadT {
    IMAGE_ADDED, IMAGE_REMOVED, NUMBER_CHANGED, VALUE_ADDED, VALUE_REMOVED
}

sealed interface Payload {

    data class AddImage(val uri: Uri): Payload
    data class RemoveImage(val position: Int): Payload
    data class ChangeNumber(val num1: Double, val num2: Double): Payload
    data class AddValue(val value: Int): Payload
    data class RemoveValue(val position: Int): Payload
    data class ChangeValue(val position: Int, val value: Int): Payload
}