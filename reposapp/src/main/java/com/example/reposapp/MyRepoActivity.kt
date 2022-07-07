package com.example.reposapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.RepoViewElement
import github.data.repository.RequestRepoFeedRepositoryImpl
import github.domain.usecase.RequestRepoFeedUseCaseImpl
import github.domain.viewmodel.RepositoriesViewModel


class MyRepoActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    LiveDataObserveProtocol {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyRepoViewAdapter

    //TODO viewModels()
    private lateinit var model: RepositoriesViewModel

    private fun requestInfoDone() {
        swipeRefreshLayout.isRefreshing = false
    }

    private fun notifyDataUpdated(repositories: List<RepoViewElement>) {
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

        val requestRepoFeedRepository = RequestRepoFeedRepositoryImpl()
        val requestRepoFeedUseCase = RequestRepoFeedUseCaseImpl(requestRepoFeedRepository)
        model = RepositoriesViewModel(requestRepoFeedUseCase)

        model.requestPerUser(intent.data.toString())

        observe(model.repositories) { notifyDataUpdated(it) }
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }
}