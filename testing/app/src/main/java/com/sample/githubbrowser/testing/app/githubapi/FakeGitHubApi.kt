package com.sample.githubbrowser.testing.app.githubapi

import com.sample.githubbrowser.githubapi.GitHubApi
import com.sample.githubbrowser.githubapi.TopReposSearchResult
import com.sample.githubbrowser.githubapi.model.ContributorApiModel
import com.sample.githubbrowser.githubapi.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeGitHubApi @Inject constructor() : GitHubApi {

    var repos = listOf<RepoApiModel>()

    override suspend fun getTopRepositories(): TopReposSearchResult {
        return TopReposSearchResult(repos)
    }

    override suspend fun getRepo(repoOwner: String, repoName: String): RepoApiModel {
        TODO("Not yet implemented")
    }

    override suspend fun getContributors(
        repoOwner: String,
        repoName: String
    ): List<ContributorApiModel> {
        TODO("Not yet implemented")
    }
}