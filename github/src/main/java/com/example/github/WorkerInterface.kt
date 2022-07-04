package com.example.github

interface WorkerInterface {
    fun notifyDataUpdated(userInfo: UserInfo?, userRepos: MutableList<UserReposInfo>)
}