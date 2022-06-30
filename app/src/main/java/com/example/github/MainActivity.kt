package com.example.github

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.github.WebApi.Companion.downloadFile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onRefresh() {
        val apiInterface = GithubApi.create().getFollowers("cornway")

        apiInterface.enqueue( object : Callback<List<Followers>>{
            override fun onResponse(
                call: Call<List<Followers>>,
                response: Response<List<Followers>>
            ) {

                response.body()?.forEach {
                    Log.d("onResponse; login:", it.login)
                }
            }

            override fun onFailure(call: Call<List<Followers>>, t: Throwable) {
                Log.d("onFailure", "Failed")
            }
        })

        val apiGetUser = GithubApi.create().getUserInfo("cornway")

        apiGetUser.enqueue(object : Callback<UserInfo>{
            override fun onResponse(
                call: Call<UserInfo>,
                response: Response<UserInfo>
            ) {
                response.body()?.let {
                    var textView: TextView = findViewById(R.id.user_name)
                    textView.text = it.login

                    textView = findViewById(R.id.user_location)
                    textView.text = it.location

                    val dir = baseContext.filesDir
                    val imgFile = File(dir, "Avatar.png")
                    WebApi.downloadFile(it.avatarUrl, imgFile)

                    if (imgFile.exists()) {
                        val bitmap: Bitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                        val imageView: ImageView = findViewById(R.id.user_image)
                        imageView.setImageBitmap(bitmap)
                    }

                }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout =  findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener(this)

    }
}