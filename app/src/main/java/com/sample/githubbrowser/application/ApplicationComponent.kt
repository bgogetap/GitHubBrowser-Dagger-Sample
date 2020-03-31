package com.sample.githubbrowser.application

import android.content.Context
import com.sample.githubbrowser.appdeps.ApplicationDeps
import com.sample.githubbrowser.githubapi.GitHubApiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GitHubApiModule::class])
interface ApplicationComponent : ApplicationDeps {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}