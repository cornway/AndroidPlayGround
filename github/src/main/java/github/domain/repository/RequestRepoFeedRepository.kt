package github.domain.repository

import com.example.github.GithubApi
import com.example.github.RepoViewElement

class RequestRepoFeedRepository {
    var repositoriesViewData: List<RepoViewElement>? = null

    suspend fun requestPerUser(userName: String) {
        val githubApi = GithubApi.create().create(GithubApi::class.java)
        val repositories = githubApi.getUserRepos(userName)

        repositoriesViewData = repositories.body()?.map {
            RepoViewElement(it.fullName, it.owner?.login, it.owner?.avatarUrl)
        }
    }

    suspend fun requestFeed(since: Int, perPage: Int) {
        val githubApi = GithubApi.create().create(GithubApi::class.java)
        val repositories = githubApi.getRepos(since.toString(), perPage.toString()).body()

        repositoriesViewData = repositories?.map {
            RepoViewElement(it.fullName, it.owner?.login, it.owner?.avatarUrl)
        }
    }
}