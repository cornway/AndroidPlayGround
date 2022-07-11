package com.github.data

interface RequestDataRepositories {
    suspend fun requestPerUser(userName: String) : Array<Repositories>?
    suspend fun requestFeed(since: Int, perPage: Int) : Array<Repositories>?
}