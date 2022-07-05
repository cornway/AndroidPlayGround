package com.example.github

import com.example.example.Repositories

interface WorkerInterface<T> {
    fun notifyDataUpdated(userInfo: UserInfo?, repositories: Array<T>?) {}
}