package github.domain.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.github.RepoViewElement
import github.domain.usecase.RequestRepoFeedUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RepositoriesViewModel: BaseViewModel() {

    private val requestRepoFeedUseCase by inject<RequestRepoFeedUseCase>()

    val repositories: MutableLiveData<List<RepoViewElement>> by lazy {
        MutableLiveData<List<RepoViewElement>>()
    }

    fun requestPerUser (userName: String) {
        execute {
            val value = requestRepoFeedUseCase.requestPerUser(userName)
            value?.let {
                repositories.value = it
            }
        }
    }

    fun requestFeed (since: Int, perPage: Int) {
        execute {
            val value = requestRepoFeedUseCase.requestFeed(since, perPage)
            value?.let {
                repositories.value = it
            }
        }
    }
}