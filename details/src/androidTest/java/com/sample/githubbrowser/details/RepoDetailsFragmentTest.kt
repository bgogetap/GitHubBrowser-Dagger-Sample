package com.sample.githubbrowser.details

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.githubbrowser.githubapi.model.ContributorApiModel
import com.sample.githubbrowser.githubapi.model.RepoApiModel
import com.sample.githubbrowser.githubapi.model.UserApiModel
import com.sample.githubbrowser.testing.app.TestApplication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoDetailsFragmentTest {

    private val fakeRepoApiModel = RepoApiModel(
        id = 1L,
        name = "Home Fragment",
        description = "Mock Repo Description",
        owner = UserApiModel(id = 1L, login = "dagger"),
        stargazersCount = 1,
        forksCount = 1,
        contributorsUrl = "",
        createdDate = "1/1/2020",
        updatedDate = "1/1/2020"
    )

    private val fakeContributor =
        ContributorApiModel(id = 1L, login = "contributor", avatarUrl = "avatar.png")

    @Before
    fun setUp() {
        val gitHubApi = TestApplication.component.gitHubApi()
        gitHubApi.singleRepoResult = fakeRepoApiModel
        gitHubApi.contributorsResult = listOf(fakeContributor)
    }

    @Test
    fun loadedStateDisplaysExpectedData() {
        launchFragmentInContainer<RepoDetailsFragment>(
            fragmentArgs = Bundle().apply {
                putString("repo_owner", "owner")
                putString("repo_name", "name")
            }
        )

        onView(withId(R.id.details_loading_indicator))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.contributor_loading_indicator))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))

        onView(withId(R.id.repo_name)).check(matches(withText(fakeRepoApiModel.name)))
        onView(withId(R.id.repo_description))
            .check(matches(withText(fakeRepoApiModel.description)))

        onView(withId(R.id.contributorName)).check(matches(withText(fakeContributor.login)))
    }
}