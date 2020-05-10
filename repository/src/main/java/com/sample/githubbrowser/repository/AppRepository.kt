package com.sample.githubbrowser.repository

import com.sample.githubbrowser.githubapi.GitHubApi
import com.sample.githubbrowser.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val gitHubApi: GitHubApi
) {

    suspend fun getTopRepos(): List<RepoApiModel> {
        return gitHubApi.getTopRepositories().items
    }
}