package com.example.github

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.example.Repositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.sin

class Worker (private val workerInterface: WorkerInterface<Repositories>) {

    suspend fun requestInfo(userName: String) {
        val githubApi = GithubApi.create().create(GithubApi::class.java)
        var userInfo: UserInfo? = githubApi.getUserInfo(userName).body()
        val repositories = githubApi.getUserRepos(userName).body()

        workerInterface.notifyDataUpdated(userInfo, repositories)
    }

    suspend fun requestRepositories(since: Int, perPage: Int) {
        val githubApi = GithubApi.create().create(GithubApi::class.java)
        val repositories = githubApi.getRepos(since.toString(), perPage.toString())

        workerInterface.notifyDataUpdated(null, repositories.body())
    }

}