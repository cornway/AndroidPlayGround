package com.github.domain.repository

import com.github.ui.RepoViewElement

interface RequestRepoFeedRepository {
    suspend fun requestPerUser(userName: String): List<RepoViewElement>?
    suspend fun requestFeed(since: Int, perPage: Int): List<RepoViewElement>?
}