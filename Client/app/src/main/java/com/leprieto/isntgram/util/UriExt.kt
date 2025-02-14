package com.leprieto.isntgram.util

import android.content.Context
import android.net.Uri
import java.io.File

fun Uri.toFile(context: Context): File {
    val inputStream = context.contentResolver.openInputStream(this)
    val file = File.createTempFile("temp_image", null, context.cacheDir)
    file.outputStream().use {
        inputStream?.copyTo(it)
    }
    return file;
}