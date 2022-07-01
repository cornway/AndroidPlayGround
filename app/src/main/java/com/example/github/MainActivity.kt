package com.example.github

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ViewAdapter

    private fun requestInfo(userName: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            val githubApi = GithubApi.create().create(GithubApi::class.java)

            val result = githubApi.getUserInfo(userName)
            result.let {
                val userInfo = it.body()

                userInfo?.let {
                    var textView: TextView = findViewById(R.id.user_name)
                    textView.text = userInfo.login

                    textView = findViewById(R.id.user_location)
                    textView.text = userInfo.location

                    val imageView: ImageView = findViewById(R.id.user_image)
                    Glide.with(imageView)
                        .load(userInfo.avatarUrl)
                        .into(imageView)
                }
            }
        }
    }

    private fun requestRepos(userName: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            val githubApi = GithubApi.create().create(GithubApi::class.java)

            val result = githubApi.getUserRepos(userName)
            val userRepos = result.body()
            val list = viewAdapter.dataSet
            userRepos?.let {
                userRepos.forEach {
                    list.add(ViewElement(it.name, it.url))
                }
            }
            viewAdapter.notifyDataSetChanged()
        }
    }

    override fun onRefresh() {
        requestInfo("cornway")
        requestRepos("cornway")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout =  findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener(this)

        recyclerView = findViewById(R.id.recycler_view)
        viewAdapter = ViewAdapter()
        recyclerView.adapter = viewAdapter

    }
}