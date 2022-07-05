package com.example.mydiffutil

import androidx.recyclerview.widget.DiffUtil

class UserDiffUtilCallback<T : UserDiffUtilInterface<T>>(private val oldList: List<T>, private val newList: List<T>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].theSame(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].contentTheSame(newList[newItemPosition])
    }

}