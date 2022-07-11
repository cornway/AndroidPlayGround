package com.github.domain.usecase

import com.github.domain.repository.RequestRepoFeedRepository
import com.github.ui.RepoViewElement
import org.koin.core.component.KoinComponent

class RequestRepoFeedUseCaseImpl(
    private var requestRepoFeedRepository: RequestRepoFeedRepository
) : RequestRepoFeedUseCase, KoinComponent {

    override suspend fun requestPerUser(userName: String): List<RepoViewElement>? {
        return requestRepoFeedRepository.requestPerUser(userName)
    }

    override suspend fun requestFeed(since: Int, perPage: Int): List<RepoViewElement>? {
        return requestRepoFeedRepository.requestFeed(since, perPage)
    }
}