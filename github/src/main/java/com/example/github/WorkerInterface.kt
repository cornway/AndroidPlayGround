package com.example.github

import com.example.example.Repositories

interface WorkerInterface {
    fun notifyDataUpdated(userInfo: UserInfo?, userRepos: MutableList<UserReposInfo>) {}
    fun notifyReposUpdated(repos: MutableList<Repositories>) {}
}