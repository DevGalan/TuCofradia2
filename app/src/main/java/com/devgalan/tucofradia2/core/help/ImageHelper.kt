package com.devgalan.tucofradia2.core.help

import android.content.Context
import android.net.Uri
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ImageHelper {
    fun getFileFromUri(context: Context, uri: Uri): File {
        val inputStream: InputStream = context.contentResolver.openInputStream(uri)!!
        val file = File(context.cacheDir, "image.png")
        file.createNewFile()

        val outputStream = FileOutputStream(file)
        outputStream.use { output ->
            val buffer = ByteArray(4 * 1024) // or other buffer size
            var read: Int
            while (inputStream.read(buffer).also { read = it } != -1) {
                output.write(buffer, 0, read)
            }
            output.flush()
        }
        return file
    }

    fun getMultipartFromFile(file: File, paramName: String): MultipartBody.Part {
        val requestBody = RequestBody.create(MediaType.parse("*/*"), file)
        return MultipartBody.Part.createFormData(paramName, file.name, requestBody)
    }
}