package com.example.github

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class WebApi {
    companion object {

        private fun writeResponseToDisk(responseBody: ResponseBody?, file: File) {
            responseBody?.let {
                var byteArray: ByteArray = ByteArray(4096);
                var bytestream = it.byteStream()
                var outputStream = FileOutputStream(file)
                var read: Int = 0;
                try {
                    while (read != -1) {
                        read = bytestream.read(byteArray)
                        if (read != -1) {
                            outputStream.write(byteArray)
                        }
                    }
                } catch (e: IOException) {
                    Log.d("writeResponseToDisk", "Shit!")
                }

            }
        }
    }
}