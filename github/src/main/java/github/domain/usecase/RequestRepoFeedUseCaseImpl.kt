package github.domain.usecase

import com.example.github.RepoViewElement
import github.domain.repository.RequestRepoFeedRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

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