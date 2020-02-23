package com.sample.githubbrowser.githubapi

import com.sample.githubbrowser.githubapi.model.RepoApiModel
import com.sample.githubbrowser.githubapi.model.UserApiModel

interface GitHubApi {

    fun getTopRepositories(): List<RepoApiModel>
}

class MockGitHubApi : GitHubApi {
    override fun getTopRepositories(): List<RepoApiModel> {
        return listOf(
            RepoApiModel(
                id = 1L,
                name = "Mock Repo",
                description = "Mock Repo Description",
                owner = UserApiModel(id = 1L, login = "dagger"),
                stargazersCount = 1,
                forksCount = 1,
                contributorsUrl = "",
                createdDate = "1/1/2020",
                updatedDate = "1/1/2020"
            )
        )
    }

}