package com.github.data.offline

import com.github.data.Repositories
import com.github.data.RequestDataRepositories

class RequestDataRepositoriesOfflineImpl : RequestDataRepositories {
    override suspend fun requestPerUser(userName: String): Array<Repositories>? {
        val owner = Repositories.Owner(login = "NoName")
        val repo = Repositories(name = "NoName", fullName = "NoFullName", owner = owner)
        return Array(20){repo}
    }

    override suspend fun requestFeed(since: Int, perPage: Int): Array<Repositories>? {
        val owner = Repositories.Owner(login = "NoName : ")
        return Array(20){i -> Repositories(name = "NoName", fullName = "NoFullName", owner = owner).apply {
            fullName += (i+since).toString()
        }}
    }
}