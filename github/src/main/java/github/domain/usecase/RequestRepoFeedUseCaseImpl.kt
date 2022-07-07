package github.domain.usecase

import com.example.github.RepoViewElement
import github.domain.repository.RequestRepoFeedRepository

class RequestRepoFeedUseCaseImpl(
    private val requestRepoFeedRepository: RequestRepoFeedRepository
) : RequestRepoFeedUseCase {
    override suspend fun requestPerUser(userName: String): List<RepoViewElement>? {
        return requestRepoFeedRepository.requestPerUser(userName)
    }

    override suspend fun requestFeed(since: Int, perPage: Int): List<RepoViewElement>? {
        return requestRepoFeedRepository.requestFeed(since, perPage)
    }
}