package com.sample.githubbrowser.githubapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContributorApiModel(
    val id: Long,
    val login: String,
    @Json(name = "avatar_url") val avatarUrl: String
)