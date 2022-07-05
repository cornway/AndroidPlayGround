package com.example.reposapp

import com.example.mydiffutil.UserDiffUtilInterface

data class RepoViewElement (
    val name: String,
    val avatarUrl: String?
) : UserDiffUtilInterface<RepoViewElement> {
    override fun theSame(item: RepoViewElement): Boolean {
        return name == item.name
    }

    override fun contentTheSame(item: RepoViewElement): Boolean {
        return avatarUrl == item.avatarUrl
    }
}
