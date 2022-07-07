package github.domain.usecase

import com.example.example.Repositories
import com.example.github.RepoViewElement

interface RequestRepoFeedUseCase {
    suspend fun requestPerUser(username: String) : List<RepoViewElement>?
    suspend fun requestFeed(since: Int, perPage: Int) : List<RepoViewElement>?
}