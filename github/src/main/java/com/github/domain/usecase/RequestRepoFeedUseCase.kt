package com.github.domain.usecase

import com.github.data.RepositoryElement

interface RequestRepoFeedUseCase {
    suspend fun requestPerUser(username: String) : List<RepositoryElement>?
    suspend fun requestFeed(since: Int, perPage: Int) : List<RepositoryElement>?
}