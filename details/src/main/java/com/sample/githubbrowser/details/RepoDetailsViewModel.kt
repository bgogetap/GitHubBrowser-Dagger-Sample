package com.sample.githubbrowser.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.githubbrowser.details.list.ContributorItem
import com.sample.githubbrowser.di.scope.ScreenScope
import com.sample.githubbrowser.repository.AppRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@ScreenScope
class RepoDetailsViewModel @Inject constructor(
    @Named("repo_owner") private val repoOwner: String,
    @Named("repo_name") private val repoName: String,
    private val appRepository: AppRepository
) : ViewModel() {

    private val repoInfoViewState = MutableLiveData<RepoInfoViewState>(RepoInfoViewStateLoading)
    val repoInfoUpdates: LiveData<RepoInfoViewState> = repoInfoViewState

    private val contributorsViewState =
        MutableLiveData<RepoContributorsViewState>(RepoContributorsViewStateLoading)
    val contributorsUpdates: LiveData<RepoContributorsViewState> = contributorsViewState

    init {
        viewModelScope.launch {
            val repo = appRepository.getRepo(repoOwner, repoName)
            repoInfoViewState.value = RepoInfoViewStateLoaded(
                repoName = repo.name,
                repoDescription = repo.description ?: "",
                createdDate = repo.createdDate,
                updatedDate = repo.updatedDate
            )
        }
        viewModelScope.launch {
            val contributors = appRepository.getContributors(repoOwner, repoName)
            contributorsViewState.value = RepoContributorsViewStateLoaded(
                contributors = contributors.map { apiModel ->
                    ContributorItem(
                        id = apiModel.id,
                        name = apiModel.login,
                        avatarUrl = apiModel.avatarUrl
                    )
                }
            )
        }
    }
}