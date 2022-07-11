package com.github.domain.repository

import com.github.data.RepositoryElement

interface RequestRepoFeedRepository {
    suspend fun requestPerUser(userName: String): List<RepositoryElement>?
    suspend fun requestFeed(since: Int, perPage: Int): List<RepositoryElement>?
}