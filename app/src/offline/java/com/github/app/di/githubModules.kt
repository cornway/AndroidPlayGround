package com.github.app.di

import com.github.data.RequestDataRepositories
import com.github.data.offline.RequestDataRepositoriesOfflineImpl
import com.github.domain.repository.RequestRepoFeedRepository
import com.github.domain.usecase.RequestRepoFeedUseCase
import com.github.domain.usecase.RequestRepoFeedUseCaseImpl
import com.github.domain.viewmodel.RepositoriesViewModel
import com.github.domain.repository.RequestRepoFeedRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val githubModules = module {

    single { RequestDataRepositoriesOfflineImpl() as RequestDataRepositories }
    single { RequestRepoFeedRepositoryImpl(get()) as RequestRepoFeedRepository}
    single { RequestRepoFeedUseCaseImpl(get()) as RequestRepoFeedUseCase }
    viewModel { RepositoriesViewModel(get()) }
}