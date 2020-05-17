package com.sample.githubbrowser.details

import androidx.lifecycle.ViewModel
import com.sample.githubbrowser.di.scope.ScreenScope
import com.sample.githubbrowser.repository.AppRepository
import javax.inject.Inject
import javax.inject.Named

@ScreenScope
class RepoDetailsViewModel @Inject constructor(
    @Named("repo_owner") private val repoOwner: String,
    @Named("repo_name") private val repoName: String,
    private val appRepository: AppRepository
) : ViewModel()