package com.example.reposapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.mydiffutil.UserDiffUtilCallback

class MyRepoViewAdapter ():
    RecyclerView.Adapter<MyRepoViewAdapter.ViewHolder>() {

        private var dataSet: MutableList<MyRepoViewElement> = mutableListOf()

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val userRepoName: TextView?
            val userRepoUrl: TextView?
            val userAvatar: ImageView?

            init {
                userRepoName = view.findViewById(R.id.view_user_repo_name)
                userRepoUrl = view.findViewById(R.id.view_user_repo_url)
                userAvatar = view.findViewById(R.id.view_user_avatar)
            }
        }

    fun setData(updatedDataSet: List<MyRepoViewElement>?) {
        updatedDataSet?.let {
            val diffResult = DiffUtil.calculateDiff(UserDiffUtilCallback(dataSet, updatedDataSet))
            dataSet.clear()
            dataSet.addAll(updatedDataSet)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userRepoName?.text = dataSet[position].name
        holder.userRepoUrl?.text = dataSet[position].url

        holder.userAvatar?.let {
            Glide.with(it)
                .load(dataSet[position].avatarUrl)
                .into(it)

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}