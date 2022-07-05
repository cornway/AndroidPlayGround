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
import com.example.example.Repositories
import com.example.github.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyRepoActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    WorkerInterface<Repositories> {
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

    override fun notifyDataUpdated(userInfo: UserInfo?, repositories: Array<Repositories>?) {
        val owner = repositories?.get(0)?.owner
        owner?.let {
            var textView: TextView = findViewById(R.id.user_name)
            textView.text = it.login

            textView = findViewById(R.id.user_location)
            //TODO
            textView.text = "Location"

            val imageView: ImageView = findViewById(R.id.user_image)
            Glide.with(this)
                .load(it.avatarUrl)
                .into(imageView)
        }

        val list = repositories?.map {
            MyRepoViewElement(it.owner?.login, it.url, it.owner?.avatarUrl)
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