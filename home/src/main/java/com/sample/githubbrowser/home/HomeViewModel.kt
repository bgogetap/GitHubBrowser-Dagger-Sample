package com.sample.githubbrowser.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.githubbrowser.di.scope.ScreenScope
import com.sample.githubbrowser.home.list.RepoItem
import com.sample.githubbrowser.navigation.DetailsScreen
import com.sample.githubbrowser.navigation.ScreenNavigator
import com.sample.githubbrowser.repository.AppRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@ScreenScope
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val screenNavigator: ScreenNavigator
) : ViewModel() {

    private val _viewState = MutableLiveData<HomeViewState>(HomeViewStateLoading)
    val viewStateUpdates: LiveData<HomeViewState> = _viewState

    init {
        viewModelScope.launch {
            val topRepos = appRepository.getTopRepos()
            _viewState.value = HomeViewStateLoaded(
                repos = topRepos.map {
                    RepoItem(
                        ownerName = it.owner.login,
                        name = it.name,
                        description = it.description ?: "",
                        starsCount = it.stargazersCount,
                        forkCount = it.forksCount
                    )
                }
            )
        }
    }

    fun onRepoSelected(repoOwner: String, repoName: String) {
        screenNavigator.goToScreen(DetailsScreen(repoOwner, repoName))
    }
}