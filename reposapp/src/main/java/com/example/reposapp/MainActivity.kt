package com.example.reposapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.github.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    WorkerInterface {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ViewAdapter
    private var worker: Worker? = null;

    override fun onRefresh() {
        worker?.requestInfo("cornway")
    }

    override fun notifyDataUpdated(userInfo: UserInfo?, userRepos: MutableList<UserReposInfo>) {
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

        viewAdapter.dataSet.clear()
        userRepos.forEach() {
            viewAdapter.dataSet.add(ViewElement(it.name, it.url))
        }
        viewAdapter.notifyDataSetChanged()
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout =  findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener(this)

        recyclerView = findViewById(R.id.recycler_view)
        viewAdapter = ViewAdapter()
        recyclerView.adapter = viewAdapter

        worker = Worker(lifecycleScope, this)

    }
}