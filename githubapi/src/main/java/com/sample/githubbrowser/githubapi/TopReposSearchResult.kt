package com.sample.githubbrowser.githubapi

import com.sample.githubbrowser.githubapi.model.RepoApiModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopReposSearchResult(val items: List<RepoApiModel>)