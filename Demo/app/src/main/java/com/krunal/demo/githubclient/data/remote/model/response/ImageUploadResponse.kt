package com.krunal.demo.githubclient.data.remote.model.response

data class ImageUploadResponse(
    val `data`: Data, val status: Int, val success: Boolean
)

data class Medium(
    val extension: String, val filename: String, val mime: String, val name: String, val url: String
)

data class Image(
    val extension: String, val filename: String, val mime: String, val name: String, val url: String
)

data class Data(
    val deleteUrl: String,
    val displayUrl: String,
    val expiration: String,
    val height: String,
    val id: String,
    val image: Image,
    val medium: Medium,
    val size: String,
    val thumb: Thumb,
    val time: String,
    val title: String,
    val url: String,
    val urlViewer: String,
    val width: String
)

data class Thumb(
    val extension: String, val filename: String, val mime: String, val name: String, val url: String
)