package com.krunal.demo.githubclient.util

import android.content.Context
import android.net.Uri
import android.provider.MediaStore


fun getPathFromUri(context: Context, uri: Uri): String? =
    context.contentResolver.query(uri, null, null, null, null).use { cursor ->
        if (cursor == null) {
            uri.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            cursor.getString(idx)
        }
    }