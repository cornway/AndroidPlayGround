package com.example.github

import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Worker (var lifecycleScope: LifecycleCoroutineScope, val activity: MainActivity) {

    data class UserReposInfo(
        var name: String,
        var url: String
    )

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

            activity.notifyDataUpdated(userInfo, userReposInfo)
        }
    }

}