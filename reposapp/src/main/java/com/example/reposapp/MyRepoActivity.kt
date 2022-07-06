package com.example.reposapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.example.Repositories
import com.example.github.R
import github.domain.viewmodel.RepositoriesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyRepoActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyRepoViewAdapter
    //TODO viewModels()
    private lateinit var model: RepositoriesViewModel

    private fun requestInfoDone() {
        swipeRefreshLayout.isRefreshing = false
    }

    private fun notifyDataUpdated(repositories: Array<Repositories>) {
        val owner = repositories[0].owner
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

        val list = repositories.map {
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

        model = RepositoriesViewModel(intent.data.toString())

        lifecycleScope.launch(Dispatchers.Main) {
            model.invoke()
        }

        model.repositories.observe(this) { notifyDataUpdated(it) }
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }
}