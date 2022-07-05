package com.example.reposapp

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.example.Repositories
import com.example.github.R
import com.example.github.Worker
import com.example.github.WorkerInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllRepoActivity : AppCompatActivity(), WorkerInterface {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RepoViewAdapter
    private val preloadDataThreshold: Int = 60
    private val preloadPerPage: Int = 10
    private var requestPending: Boolean = false
    private var worker: Worker? = null;

    private fun requestData() {
        worker?.let {
            requestPending = true
            lifecycleScope.launch(Dispatchers.Main) {
                let@it.requestRepositories(viewAdapter.itemCount, preloadPerPage)
            }
        }
    }

    private fun requestDataDone() {
        requestPending = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        recyclerView = findViewById(R.id.recycler_view_repos)
        viewAdapter = RepoViewAdapter()

        recyclerView.adapter = viewAdapter

        worker = Worker(this)

        requestData()

        recyclerView.setOnScrollChangeListener(object : View.OnScrollChangeListener {
            override fun onScrollChange(
                v: View?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                if (!requestPending) {
                    val index = (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstCompletelyVisibleItemPosition()?:0
                    if (viewAdapter.itemCount - preloadDataThreshold < index) {
                        requestData()
                    }
                }
            }
        })

    }

    override fun notifyReposUpdated(repos: MutableList<Repositories>) {
        val list = repos.map {
            RepoViewElement(it.name?:"Not Found", it.owner?.avatarUrl)
        }
        viewAdapter.appendData(list)
        requestDataDone()
    }
}