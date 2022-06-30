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
        fun downloadFile(url: String, path: File) {
            val api = GithubApi.create().getAvatar(url)

            api.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        writeResponseToDisk(response.body(), path)
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

        fun writeResponseToDisk(responseBody: ResponseBody?, file: File) {
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