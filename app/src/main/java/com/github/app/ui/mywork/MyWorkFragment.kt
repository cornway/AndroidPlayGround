package com.github.app.ui.mywork

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.github.R
import com.github.app.ui.common.BaseFragment
import com.github.data.RepositoryElement
import com.github.domain.viewmodel.RepositoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyWorkFragment : BaseFragment(R.layout.fragment_my_work) {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyRepoViewAdapter
    private lateinit var ownerNameView: TextView
    private lateinit var ownerLocationView: TextView
    private lateinit var ownerAvatarView: ImageView

    private val model: RepositoriesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ownerNameView = view.findViewById(R.id.user_name)
        ownerLocationView = view.findViewById(R.id.user_location)
        ownerAvatarView = view.findViewById(R.id.user_image)

        recyclerView = view.findViewById(R.id.recycler_view)
        viewAdapter = MyRepoViewAdapter()
        recyclerView.adapter = viewAdapter

        val ownerName = arguments?.getString("ownerName")

        model.requestPerUser(ownerName.orEmpty())

        observe(model.repositories) { notifyDataUpdated(it) }
    }

    private fun notifyDataUpdated(repositories: List<RepositoryElement>) {
        ownerNameView.text = repositories[0].ownerName
        ownerLocationView.text = "Location"

        Glide.with(this)
            .load(repositories[0].avatarUrl)
            .into(ownerAvatarView)

        viewAdapter.setData(repositories)
    }
}