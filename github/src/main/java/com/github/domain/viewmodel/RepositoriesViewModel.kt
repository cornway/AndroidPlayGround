package com.github.domain.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.domain.usecase.RequestRepoFeedUseCase
import com.github.data.RepositoryElement

class RepositoriesViewModel(
    private val requestRepoFeedUseCase: RequestRepoFeedUseCase
): BaseViewModel() {

    val repositories: MutableLiveData<List<RepositoryElement>> by lazy {
        MutableLiveData<List<RepositoryElement>>()
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