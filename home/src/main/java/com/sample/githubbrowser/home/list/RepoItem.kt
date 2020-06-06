package com.sample.githubbrowser.home.list

import com.sample.githubbrowser.poweradapter.RecyclerItem

data class RepoItem(
    val ownerName: String,
    val name: String,
    val description: String,
    val starsCount: Int,
    val forkCount: Int
) : RecyclerItem {
    override fun getId(): Long {
        return ("$ownerName.$name".hashCode()).toLong()
    }
}