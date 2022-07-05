package com.example.reposapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Repositories
import com.example.github.R
import com.example.github.UserInfo
import com.example.github.Worker
import com.example.github.WorkerInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoriesActivity : AppCompatActivity(), WorkerInterface<Repositories> {
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

    override fun notifyDataUpdated(userInfo: UserInfo?, repositories: Array<Repositories>?) {
        val list = repositories?.map {
            RepoViewElement(it.name?:"null", it.owner?.login, it.owner?.avatarUrl)
        }
        viewAdapter.onItemClick = {
            val intent = Intent(this, MyRepoActivity::class.java)
            intent.data = (Uri.parse(it))
            startActivity(intent)
        }
        viewAdapter.appendData(list)
        requestDataDone()
    }
}