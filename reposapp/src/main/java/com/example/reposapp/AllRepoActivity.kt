package com.example.reposapp

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
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

        worker?.requestRepositories(0)

    }

    override fun notifyReposUpdated(repos: MutableList<Repositories>) {
        var start = viewAdapter.dataSet.size
        repos.forEach {
            var bitmap: Bitmap? = null

            Glide.with(this)
                .asBitmap()
                .load(it.owner?.avatarUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        bitmap = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }

                })

            viewAdapter.dataSet.add(start++, RepoViewElement(it.fullName?:"Not Found", bitmap))
        }
        viewAdapter.notifyDataSetChanged()
    }
}