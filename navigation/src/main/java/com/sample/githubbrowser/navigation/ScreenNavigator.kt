package com.sample.githubbrowser.navigation

import android.annotation.SuppressLint
import android.app.Activity

sealed class Screen
data class DetailsScreen(val repoOwner: String, val repoName: String) : Screen()

interface ScreenNavigator {

    fun goToScreen(screen: Screen)
}

interface NavigationDeps {

    fun screenNavigator(): ScreenNavigator
}

const val NAVIGATION_DEPS_SERVICE = "com.sample.githubbrowser.navigationdeps"

fun Activity.navigationDeps(): NavigationDeps {
    @SuppressLint("WrongConstant")
    val navigationDeps = getSystemService(NAVIGATION_DEPS_SERVICE) as? NavigationDeps
        ?: applicationContext.getSystemService(NAVIGATION_DEPS_SERVICE) as? NavigationDeps

    return navigationDeps ?: throw NullPointerException(
        "Activity must override getSystemService and provide NavigationDeps " +
                "for service name: $NAVIGATION_DEPS_SERVICE"
    )
}