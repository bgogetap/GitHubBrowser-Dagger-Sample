package com.sample.githubbrowser.repository

import com.google.common.truth.Truth.assertThat
import com.sample.githubbrowser.githubapi.model.ContributorApiModel
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

    private val fakeGitHubApi = FakeGitHubApi().apply { topRepos = listOf(fakeRepoApiModel) }

    @Before
    fun setUp() {
        appRepository = AppRepository(fakeGitHubApi)
    }

    @Test
    fun `getTopRepos returns result from GitHubApi`() {
        val topRepos = runBlocking { appRepository.getTopRepos() }

        assertThat(topRepos.size).isEqualTo(1)
        assertThat(topRepos[0]).isEqualTo(fakeRepoApiModel)
    }

    @Test
    fun `getTopRepos returns cached result`() {
        val initialRequest = runBlocking { appRepository.getTopRepos() }

        // Change API return value
        fakeGitHubApi.topRepos = listOf(fakeRepoApiModel, fakeRepoApiModel)

        val secondRequest = runBlocking { appRepository.getTopRepos() }

        assertThat(initialRequest).isEqualTo(secondRequest)
    }

    @Test
    fun `getRepo returns cached value`() {
        // Seed cache
        runBlocking { appRepository.getTopRepos() }

        // Set API to return different model on single repo fetch
        fakeGitHubApi.singleRepoResult = fakeRepoApiModel.copy(name = "Updated Name")

        val singleRepoFetchResult = runBlocking {
            appRepository.getRepo(
                repoOwner = fakeRepoApiModel.owner.login,
                repoName = fakeRepoApiModel.name
            )
        }

        assertThat(singleRepoFetchResult).isEqualTo(fakeRepoApiModel)
    }

    @Test
    fun `getRepo returns API value if not in cache`() {
        // Seed cache
        runBlocking { appRepository.getTopRepos() }

        val expectedModel = fakeRepoApiModel.copy(name = "Updated Name")
        fakeGitHubApi.singleRepoResult = expectedModel

        val singleRepoFetchResult = runBlocking {
            appRepository.getRepo(
                repoOwner = expectedModel.owner.login,
                repoName = expectedModel.name
            )
        }

        assertThat(singleRepoFetchResult).isEqualTo(expectedModel)
    }

    @Test
    fun `getContributors returns API value`() {
        val expectedContributors = listOf(
            ContributorApiModel(
                id = 1L,
                login = "contributor",
                avatarUrl = "avatar.png"
            )
        )

        fakeGitHubApi.contributorsResult = expectedContributors

        val contributors = runBlocking {
            appRepository.getContributors(fakeRepoApiModel.owner.login, fakeRepoApiModel.name)
        }

        assertThat(contributors).isEqualTo(expectedContributors)
    }
}
