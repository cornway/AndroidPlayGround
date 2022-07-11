package com.github.ui

import com.example.mydiffutil.UserDiffUtilInterface

data class RepoViewElement(
    val name: String?,
    val ownerName: String?,
    val avatarUrl: String?
) : UserDiffUtilInterface<RepoViewElement> {
    override fun theSame(item: RepoViewElement): Boolean {
        return name == item.name
    }

    override fun contentTheSame(item: RepoViewElement): Boolean {
        return avatarUrl == item.avatarUrl &&
                ownerName == item.ownerName
    }
}
