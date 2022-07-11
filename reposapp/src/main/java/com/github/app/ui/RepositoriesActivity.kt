package com.github.app.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.github.R
import com.github.domain.viewmodel.RepositoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesActivity : BaseActivity<RepositoriesViewModel>() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RepoViewAdapter
    private val preloadDataThreshold: Int = 60
    private var requestPending: Boolean = false
    private val model: RepositoriesViewModel by viewModel()
    //TODO viewModels()

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

        model.requestFeed(viewAdapter.itemCount, 100)

        observe(model.repositories) { viewAdapter.appendData(it) }
    }
}


