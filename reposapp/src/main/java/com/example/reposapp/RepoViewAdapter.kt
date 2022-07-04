package com.example.reposapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github.R

class RepoViewAdapter() : RecyclerView.Adapter<RepoViewAdapter.ViewHolder>(){

    var dataSet: MutableList<RepoViewElement> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView?
        val avatarView: ImageView?

        init {
            nameView = view.findViewById(R.id.repos_view_item_text)
            avatarView = view.findViewById(R.id.repos_view_item_avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repos_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameView?.text = dataSet[position].name
        holder.avatarView?.let {
            Glide.with(it)
                .load(dataSet[position].avatarUrl)
                .into(it)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}