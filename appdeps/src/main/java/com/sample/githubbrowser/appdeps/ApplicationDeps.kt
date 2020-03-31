package com.sample.githubbrowser.appdeps

import android.content.Context
import com.sample.githubbrowser.repository.AppRepository

interface ApplicationDeps {

    fun appRepository(): AppRepository
}

fun Context.applicationDeps(): ApplicationDeps {
    return (applicationContext as? HasApplicationDeps)?.getApplicationDeps()
        ?: throw IllegalArgumentException("Application must implement HasApplicationDeps")
}