package com.github.domain.usecase

import com.github.domain.repository.RequestRepoFeedRepository
import com.github.data.RepositoryElement
import org.koin.core.component.KoinComponent

class RequestRepoFeedUseCaseImpl(
    private var requestRepoFeedRepository: RequestRepoFeedRepository
) : RequestRepoFeedUseCase, KoinComponent {

    override suspend fun requestPerUser(userName: String): List<RepositoryElement>? {
        return requestRepoFeedRepository.requestPerUser(userName)
    }

    override suspend fun requestFeed(since: Int, perPage: Int): List<RepositoryElement>? {
        return requestRepoFeedRepository.requestFeed(since, perPage)
    }
}