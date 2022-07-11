package com.github.app.ui.trendingfeed

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.github.R
import com.github.app.ui.common.BaseFragment
import com.github.domain.viewmodel.RepositoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendingFeedFragment : BaseFragment(R.layout.fragment_trending_feed) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ViewAdapter
    private val preloadDataThreshold: Int = 60
    private val model: RepositoriesViewModel by viewModel()

    fun setupListeners() {
        recyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
            val index =
                (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstCompletelyVisibleItemPosition()
                    ?: 0
            if (viewAdapter.itemCount - preloadDataThreshold < index) {
                model.requestFeed(viewAdapter.itemCount, 100)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view_repos)
        viewAdapter = ViewAdapter()
        recyclerView.adapter = viewAdapter

        setupListeners()

        model.requestFeed(viewAdapter.itemCount, 100)

        observe(model.repositories) { viewAdapter.appendData(it) }
    }

}