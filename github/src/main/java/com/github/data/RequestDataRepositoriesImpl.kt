package com.github.data

class RequestDataRepositoriesImpl<T> : RequestDataRepositories {
    override suspend fun requestPerUser(userName: String): Array<Repositories>? {
        val githubApi = GithubApi.build().create(GithubApi::class.java)
        val repositories = githubApi.getUserRepos(userName)

        return repositories.body()
    }

    override suspend fun requestFeed(since: Int, perPage: Int): Array<Repositories>? {
        val githubApi = GithubApi.build().create(GithubApi::class.java)
        val repositories = githubApi.getRepos(since.toString(), perPage.toString())

        return repositories.body()
    }
}