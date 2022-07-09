package com.example.reposapp

import android.app.Application
import github.domain.viewmodel.modules.githubModules
import org.koin.core.context.startKoin

class GitHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(githubModules)
        }
    }
}