package com.sample.githubbrowser.repository

import com.google.common.truth.Truth.assertThat
import com.sample.githubbrowser.githubapi.model.RepoApiModel
import com.sample.githubbrowser.githubapi.model.UserApiModel
import com.sample.githubbrowser.testing.app.githubapi.FakeGitHubApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

private val fakeRepoApiModel = RepoApiModel(
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

class AppRepositoryTest {

    private lateinit var appRepository: AppRepository

    private val fakeGitHubApi = FakeGitHubApi().apply { repos = listOf(fakeRepoApiModel) }

    @Before
    fun setUp() {
        appRepository = AppRepository(fakeGitHubApi)
    }

    @Test
    fun successfulQuery() {
        val topRepos = runBlocking { appRepository.getTopRepos() }

        assertThat(topRepos.size).isEqualTo(1)
        assertThat(topRepos[0]).isEqualTo(fakeRepoApiModel)
    }
}
