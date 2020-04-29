package com.sample.githubbrowser.testing.app.githubapi

import com.sample.githubbrowser.githubapi.GitHubApi
import dagger.Binds
import dagger.Module

@Module
interface TestGitHubApiModule {

    @Binds
    fun bindGitHubApi(fakeGitHubApi: FakeGitHubApi): GitHubApi
}