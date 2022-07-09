package github.domain.viewmodel.modules

import github.data.repository.RequestRepoFeedRepositoryImpl
import github.domain.repository.RequestRepoFeedRepository
import github.domain.usecase.RequestRepoFeedUseCase
import github.domain.usecase.RequestRepoFeedUseCaseImpl

import org.koin.dsl.module

val githubModules = module {

    single { RequestRepoFeedRepositoryImpl() as RequestRepoFeedRepository}
    single { RequestRepoFeedUseCaseImpl(get()) as RequestRepoFeedUseCase }
}