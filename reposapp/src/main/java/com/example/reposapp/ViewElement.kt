package com.example.reposapp

import android.graphics.Bitmap
import com.example.mydiffutil.UserDiffUtilInterface

data class ViewElement(
    var name: String,
    var url: String,
    val avatar: Bitmap?
) : UserDiffUtilInterface<ViewElement> {
    override fun theSame(item: ViewElement): Boolean {
        return name == item.name
    }

    override fun contentTheSame(item: ViewElement): Boolean {
        return url == item.url &&
                avatar == item.avatar
    }
}