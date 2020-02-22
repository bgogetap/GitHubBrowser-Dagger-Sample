package com.sample.githubbrowser.application

import android.app.Application

class GitHubBrowserApp: Application() {

    private val appComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}