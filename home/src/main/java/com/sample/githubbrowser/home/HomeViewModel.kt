package com.sample.githubbrowser.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.githubbrowser.di.scope.ScreenScope
import com.sample.githubbrowser.home.list.NumberItem
import com.sample.githubbrowser.home.list.RepoItem
import com.sample.githubbrowser.navigation.DetailsScreen
import com.sample.githubbrowser.navigation.ScreenNavigator
import com.sample.githubbrowser.poweradapter.RecyclerItem
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
            val listItems = mutableListOf<RecyclerItem>()
            topRepos.forEachIndexed { index, repoApiModel ->
                listItems.add(NumberItem(number = index + 1))
                listItems.add(
                    RepoItem(
                        ownerName = repoApiModel.owner.login,
                        name = repoApiModel.name,
                        description = repoApiModel.description ?: "",
                        starsCount = repoApiModel.stargazersCount,
                        forkCount = repoApiModel.forksCount
                    )
                )
            }
            _viewState.value = HomeViewStateLoaded(listItems)
        }
    }

    fun onRepoSelected(repoOwner: String, repoName: String) {
        screenNavigator.goToScreen(DetailsScreen(repoOwner, repoName))
    }
}
