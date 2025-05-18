package com.nyinyi.data.di

import com.nyinyi.data.repository.GitHubRepository
import com.nyinyi.data.repository.GitHubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsGitHubRepository(gitHubRepositoryImpl: GitHubRepositoryImpl): GitHubRepository

}