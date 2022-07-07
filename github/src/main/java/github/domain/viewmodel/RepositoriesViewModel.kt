package github.domain.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.RepoViewElement
import github.domain.usecase.RequestRepoFeedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RepositoriesViewModel(
    private val requestRepoFeedUseCase: RequestRepoFeedUseCase
): ViewModel() {

    val repositories: MutableLiveData<List<RepoViewElement>> by lazy {
        MutableLiveData<List<RepoViewElement>>()
    }

    fun requestPerUser (userName: String) {
        viewModelScope.launch {
            val value = requestRepoFeedUseCase.requestPerUser(userName)
            value?.let {
                repositories.value = it
            }
        }
    }

    fun requestFeed (since: Int, perPage: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            val value = requestRepoFeedUseCase.requestFeed(since, perPage)
            value?.let {
                repositories.value = it
            }
        }
    }
}