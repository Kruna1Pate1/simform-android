package com.krunal.demo.githubclient.extentions

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.ByteArrayInputStream

fun ByteArray.toProgressRequestBody(
    contentType: MediaType? = null,
    bufferSize: Int = 1024,
    progressCallback: (progress: Int) -> Unit
): RequestBody = object : RequestBody() {

    override fun contentType(): MediaType? = contentType

    override fun writeTo(sink: BufferedSink) {
        val length = this@toProgressRequestBody.size
        var uploaded = 0
        val buffer = ByteArray(bufferSize)
        ByteArrayInputStream(this@toProgressRequestBody).use { inputStream ->
            while (true) {
                val read = inputStream.read(buffer)
                if (read == -1) return

                uploaded += read
                sink.write(buffer, 0, read)

                val progress = (((uploaded / length.toDouble())) * 100).toInt()
                progressCallback(progress)
            }
        }
    }
}