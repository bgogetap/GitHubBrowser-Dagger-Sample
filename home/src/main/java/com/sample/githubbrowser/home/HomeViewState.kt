package com.sample.githubbrowser.home

import com.sample.githubbrowser.poweradapter.RecyclerItem

sealed class HomeViewState
object HomeViewStateLoading : HomeViewState()
data class HomeViewStateLoaded(val repos: List<RecyclerItem>) : HomeViewState()
data class HomeViewStateError(val message: String) : HomeViewState()