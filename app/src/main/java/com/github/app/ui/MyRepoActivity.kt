package com.github.app.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.github.R
import com.github.app.ui.common.BaseActivity
import com.github.domain.viewmodel.RepositoriesViewModel
import com.github.data.RepositoryElement
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyRepoActivity : BaseActivity<RepositoriesViewModel>(), SwipeRefreshLayout.OnRefreshListener
     {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyRepoViewAdapter

    val model: RepositoriesViewModel by viewModel()

    private fun requestInfoDone() {
        swipeRefreshLayout.isRefreshing = false
    }

    private fun notifyDataUpdated(repositories: List<RepositoryElement>) {
        val ownerName = repositories[0].ownerName
        var textView: TextView = findViewById(R.id.user_name)
        textView.text = ownerName

        textView = findViewById(R.id.user_location)
        //TODO
        textView.text = "Location"

        val imageView: ImageView = findViewById(R.id.user_image)
        Glide.with(this)
            .load(repositories[0].avatarUrl)
            .into(imageView)

        viewAdapter.setData(repositories)
        requestInfoDone()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener(this)

        recyclerView = findViewById(R.id.recycler_view)
        viewAdapter = MyRepoViewAdapter()
        recyclerView.adapter = viewAdapter

        model.requestPerUser(intent.data.toString())

        observe(model.repositories) { notifyDataUpdated(it) }
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }
}
