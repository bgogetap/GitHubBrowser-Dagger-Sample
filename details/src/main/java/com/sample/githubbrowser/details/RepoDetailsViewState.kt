package com.sample.githubbrowser.details

import com.sample.githubbrowser.details.list.ContributorItem

sealed class RepoInfoViewState
object RepoInfoViewStateLoading : RepoInfoViewState()
data class RepoInfoViewStateLoaded(
    val repoName: String,
    val repoDescription: String,
    val createdDate: String,
    val updatedDate: String
) : RepoInfoViewState()

sealed class RepoContributorsViewState
object RepoContributorsViewStateLoading : RepoContributorsViewState()
data class RepoContributorsViewStateLoaded(val contributors: List<ContributorItem>) :
    RepoContributorsViewState()
