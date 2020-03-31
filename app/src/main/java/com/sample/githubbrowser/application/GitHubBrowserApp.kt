package com.sample.githubbrowser.application

import android.app.Application
import com.sample.githubbrowser.appdeps.ApplicationDeps
import com.sample.githubbrowser.appdeps.HasApplicationDeps

class GitHubBrowserApp : Application(), HasApplicationDeps {

    private val appComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getApplicationDeps(): ApplicationDeps {
        return appComponent
    }
}