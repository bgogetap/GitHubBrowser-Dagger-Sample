package com.sample.githubbrowser.githubapi

import retrofit2.http.GET

interface GitHubApi {

    @GET("search/repositories?q=language:kotlin&order=desc&sort=stars")
    suspend fun getTopRepositories(): TopReposSearchResult
}
