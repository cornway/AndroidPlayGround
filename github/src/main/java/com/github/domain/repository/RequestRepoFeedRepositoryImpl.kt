package com.github.domain.repository

import com.github.data.GithubApi
import com.github.ui.RepoViewElement

class RequestRepoFeedRepositoryImpl : RequestRepoFeedRepository {

    override suspend fun requestPerUser(userName: String): List<RepoViewElement>? {
        val githubApi = GithubApi.build().create(GithubApi::class.java)
        val repositories = githubApi.getUserRepos(userName)

        val repositoriesViewData = repositories.body()?.map {
            RepoViewElement(it.fullName, it.owner?.login, it.owner?.avatarUrl)
        }
        return repositoriesViewData
    }

    override suspend fun requestFeed(since: Int, perPage: Int): List<RepoViewElement>? {
        val githubApi = GithubApi.build().create(GithubApi::class.java)
        val repositories = githubApi.getRepos(since.toString(), perPage.toString()).body()

        val repositoriesViewData = repositories?.map {
            RepoViewElement(it.fullName, it.owner?.login, it.owner?.avatarUrl)
        }
        return repositoriesViewData
    }
}