package com.sample.githubbrowser.testing.app

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class GitHubBrowserTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}