package com.github.domain.usecase

import com.github.ui.RepoViewElement

interface RequestRepoFeedUseCase {
    suspend fun requestPerUser(username: String) : List<RepoViewElement>?
    suspend fun requestFeed(since: Int, perPage: Int) : List<RepoViewElement>?
}