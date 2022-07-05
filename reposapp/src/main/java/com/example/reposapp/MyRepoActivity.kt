package com.example.reposapp

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.github.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyRepoActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    WorkerInterface {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyRepoViewAdapter
    private var worker: Worker? = null;
    private lateinit var userName: String;

    private fun requestInfo() {
        worker?.let {
            swipeRefreshLayout.isRefreshing = true
            lifecycleScope.launch(Dispatchers.Main) {
                it.requestInfo(userName)
            }
        }
    }

    fun requestInfoDone() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onRefresh() {
        requestInfo()
    }

    override fun notifyDataUpdated(userInfo: UserInfo?, userRepos: MutableList<UserReposInfo>) {
        var bitmap: Bitmap? = null
        userInfo?.let {
            var textView: TextView = findViewById(R.id.user_name)
            textView.text = userInfo.login

            textView = findViewById(R.id.user_location)
            textView.text = userInfo.location

            val imageView: ImageView = findViewById(R.id.user_image)
            Glide.with(this)
                .asBitmap()
                .load(userInfo.avatarUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        imageView.setImageBitmap(resource)
                        notifyDataUpdated@bitmap = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }

                })
        }

        val list = userRepos.map {
            MyRepoViewElement(it.name, it.url, bitmap)
        }
        viewAdapter.setData(list)
        requestInfoDone()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout =  findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener(this)

        recyclerView = findViewById(R.id.recycler_view)
        viewAdapter = MyRepoViewAdapter()
        recyclerView.adapter = viewAdapter

        worker = Worker(this)

        userName = intent.data.toString()

        requestInfo()
    }
}