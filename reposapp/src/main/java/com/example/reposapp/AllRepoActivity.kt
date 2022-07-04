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

class AllRepoActivity : AppCompatActivity(), WorkerInterface {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RepoViewAdapter
    private var worker: Worker? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        recyclerView = findViewById(R.id.recycler_view_repos)
        viewAdapter = RepoViewAdapter()

        recyclerView.adapter = viewAdapter

        worker = Worker(lifecycleScope, this)

        worker?.requestRepositories(viewAdapter.dataSet.size)

        recyclerView.setOnScrollChangeListener(object : View.OnScrollChangeListener {
            override fun onScrollChange(
                v: View?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                val index = (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstCompletelyVisibleItemPosition()?:0
                if (viewAdapter.dataSet.size - 60 < index) {
                    worker?.requestRepositories(viewAdapter.dataSet.size)
                }

            }
        })

    }

    override fun notifyReposUpdated(repos: MutableList<Repositories>) {
        val list = repos.map {
            RepoViewElement(it.name?:"Not Found", it.owner?.avatarUrl)
        }
        with(viewAdapter.dataSet) {
            addAll(size, list)
        }
        viewAdapter.notifyDataSetChanged()
    }
}