package com.github.domain.repository

import com.github.data.GithubApi
import com.github.data.RepositoryElement
import com.github.data.RequestDataRepositories

class RequestRepoFeedRepositoryImpl(
    private val requestDataRepositories: RequestDataRepositories
) : RequestRepoFeedRepository {

    override suspend fun requestPerUser(userName: String): List<RepositoryElement>? {
        val repositories = requestDataRepositories.requestPerUser(userName)

        val repositoriesViewData = repositories?.map {
            RepositoryElement(it.fullName, it.owner?.login, it.owner?.avatarUrl)
        }
        return repositoriesViewData
    }

    override suspend fun requestFeed(since: Int, perPage: Int): List<RepositoryElement>? {
        val repositories = requestDataRepositories.requestFeed(since, perPage)

        val repositoriesViewData = repositories?.map {
            RepositoryElement(it.fullName, it.owner?.login, it.owner?.avatarUrl)
        }
        return repositoriesViewData
    }
}