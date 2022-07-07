package com.example.reposapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.github.R
import github.domain.repository.RequestRepoFeedRepository
import github.domain.usecase.RequestRepoFeedUseCase
import github.domain.usecase.RequestRepoFeedUseCaseImpl
import github.domain.viewmodel.RepositoriesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoriesActivity : AppCompatActivity(), LiveDataObserveProtocol {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RepoViewAdapter
    private val preloadDataThreshold: Int = 60
    private var requestPending: Boolean = false
    //TODO viewModels()
    private lateinit var model: RepositoriesViewModel

    private fun requestDataDone() {
        requestPending = false
    }

    fun setupListeners() {
        recyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
            if (!requestPending) {
                val index =
                    (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstCompletelyVisibleItemPosition()
                        ?: 0
                if (viewAdapter.itemCount - preloadDataThreshold < index) {
                    //TODO Add useCase
                    model.requestFeed(viewAdapter.itemCount, 100)
                }
            }
        }

        viewAdapter.onItemClick = {
            val intent = Intent(this, MyRepoActivity::class.java)
            intent.data = (Uri.parse(it))
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        recyclerView = findViewById(R.id.recycler_view_repos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewAdapter = RepoViewAdapter()
        recyclerView.adapter = viewAdapter

        setupListeners()
        val requestRepoFeedRepository = RequestRepoFeedRepository()
        val requestRepoFeedUseCase = RequestRepoFeedUseCaseImpl(requestRepoFeedRepository)
        model = RepositoriesViewModel(requestRepoFeedUseCase)

        model.requestFeed(viewAdapter.itemCount, 100)

        observe(model.repositories) { viewAdapter.appendData(it) }
    }
}