package com.example.reposapp

import android.graphics.Bitmap
import com.example.mydiffutil.UserDiffUtilInterface

data class MyRepoViewElement(
    var name: String,
    var url: String,
    val avatar: Bitmap?
) : UserDiffUtilInterface<MyRepoViewElement> {
    override fun theSame(item: MyRepoViewElement): Boolean {
        return name == item.name
    }

    override fun contentTheSame(item: MyRepoViewElement): Boolean {
        return url == item.url &&
                avatar == item.avatar
    }
}