package com.example.github

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.example.Repositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.sin

class Worker (var lifecycleScope: LifecycleCoroutineScope, val workerInterface: WorkerInterface) {

    fun requestInfo(userName: String) {
            lifecycleScope.launch(Dispatchers.Main) {
            val githubApi = GithubApi.create().create(GithubApi::class.java)
            var userInfo: UserInfo? = null
            var userReposInfo: MutableList<UserReposInfo> = mutableListOf()

            val resultUser = githubApi.getUserInfo(userName)
            resultUser.let {
                userInfo = it.body()
            }

            val resultRepos = githubApi.getUserRepos(userName)
            val userRepos = resultRepos.body()

            userReposInfo.clear()
            userRepos?.let {
                userRepos.forEach {
                    userReposInfo.add(UserReposInfo(it.name?:"Not Found", it.url?:"Not Found"))
                }
            }

            workerInterface.notifyDataUpdated(userInfo, userReposInfo)
        }
    }

    fun requestRepositories(since: Int) {
        lifecycleScope.launch(Dispatchers.Main) {
            val githubApi = GithubApi.create().create(GithubApi::class.java)

            val reposInfo: MutableList<Repositories> = mutableListOf()

            val repos = githubApi.getRepos(since.toString())

            repos?.body()?.let {
                it.forEach { repo ->
                    reposInfo.add(repo)
                }
            }

            workerInterface.notifyReposUpdated(reposInfo)
        }
    }

}