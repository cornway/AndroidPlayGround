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

class RepoViewAdapter() : RecyclerView.Adapter<RepoViewAdapter.ViewHolder>(){

    private var dataSet: MutableList<RepoViewElement> = mutableListOf()
    var onItemClick: ((String) -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView?
        val avatarView: ImageView?

        init {
            nameView = view.findViewById(R.id.repos_view_item_text)
            avatarView = view.findViewById(R.id.repos_view_item_avatar)
        }
    }

    fun setData(updatedDataSet: List<RepoViewElement>) {
        val diffResult = DiffUtil.calculateDiff(UserDiffUtilCallback(dataSet, updatedDataSet))
        dataSet.clear()
        dataSet.addAll(updatedDataSet)
        diffResult.dispatchUpdatesTo(this)
    }

    fun appendData(newDataSet: List<RepoViewElement>?) {
        newDataSet?.let {
            setData(dataSet + newDataSet)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repos_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameView?.apply {
            text = dataSet[position].name
            dataSet[position].ownerName?.let { name ->
                setOnClickListener {
                    onItemClick?.invoke(name)
                }
            }
        }

        holder.avatarView?.let {
            dataSet[position].avatarUrl?.let { avatarUrl ->
                if (avatarUrl != "null") {
                    Glide.with(it)
                        .load(avatarUrl)
                        .into(it)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}