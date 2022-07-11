package com.github.app

import android.app.Application
import com.github.app.di.githubModules
import org.koin.core.context.startKoin

class GitHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(githubModules)
        }
    }
}