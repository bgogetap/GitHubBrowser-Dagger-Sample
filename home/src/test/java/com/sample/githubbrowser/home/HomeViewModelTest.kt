package com.sample.githubbrowser.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sample.githubbrowser.githubapi.model.RepoApiModel
import com.sample.githubbrowser.githubapi.model.UserApiModel
import com.sample.githubbrowser.home.list.RepoItem
import com.sample.githubbrowser.navigation.DetailsScreen
import com.sample.githubbrowser.repository.AppRepository
import com.sample.githubbrowser.testing.app.githubapi.FakeGitHubApi
import com.sample.githubbrowser.testing.app.navigation.FakeScreenNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
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

class HomeViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var viewStateValues: MutableList<HomeViewState>
    private lateinit var screenNavigator: FakeScreenNavigator

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val appRepository =
            AppRepository(FakeGitHubApi().apply { topRepos = listOf(fakeRepoApiModel) })
        viewStateValues = mutableListOf()
        screenNavigator = FakeScreenNavigator()

        viewModel = HomeViewModel(appRepository, screenNavigator)
        viewModel.viewStateUpdates.observeForever { viewStateValues.add(it) }
    }

    @Test
    fun `loaded state contains repo models`() {
        assertThat(viewStateValues.size).isEqualTo(1)
        val expectedState = HomeViewStateLoaded(
            repos = listOf(
                RepoItem(
                    ownerName = fakeRepoApiModel.owner.login,
                    name = fakeRepoApiModel.name,
                    description = fakeRepoApiModel.description ?: "",
                    starsCount = fakeRepoApiModel.stargazersCount,
                    forkCount = fakeRepoApiModel.forksCount
                )
            )
        )

        assertThat(viewStateValues[0]).isEqualTo(expectedState)
    }

    @Test
    fun `repoSelected calls goToScreen`() {
        viewModel.onRepoSelected(fakeRepoApiModel.owner.login, fakeRepoApiModel.name)

        val expectedScreen = DetailsScreen(fakeRepoApiModel.owner.login, fakeRepoApiModel.name)

        assertThat(screenNavigator.openedScreens.size).isEqualTo(1)
        assertThat(screenNavigator.openedScreens[0]).isEqualTo(expectedScreen)
    }
}
