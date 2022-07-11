package com.github.data

import com.example.mydiffutil.UserDiffUtilInterface

data class RepositoryElement(
    val name: String?,
    val ownerName: String?,
    val avatarUrl: String?
) : UserDiffUtilInterface<RepositoryElement> {
    override fun theSame(item: RepositoryElement): Boolean {
        return name == item.name
    }

    override fun contentTheSame(item: RepositoryElement): Boolean {
        return avatarUrl == item.avatarUrl &&
                ownerName == item.ownerName
    }
}
