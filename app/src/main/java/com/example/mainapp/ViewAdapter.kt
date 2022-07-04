package com.example.mainapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.github.R

class ViewAdapter ():
    RecyclerView.Adapter<ViewAdapter.ViewHolder>() {

        var dataSet: MutableList<ViewElement> = mutableListOf()

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val userRepoName: TextView?
            val userRepoUrl: TextView?

            init {
                userRepoName = view.findViewById(R.id.view_user_repo_name)
                userRepoUrl = view.findViewById(R.id.view_user_repo_url)
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
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}