package com.sample.githubbrowser.testing.app

import android.content.Context
import com.sample.githubbrowser.appcomponent.ApplicationComponent
import com.sample.githubbrowser.testing.app.githubapi.FakeGitHubApi
import com.sample.githubbrowser.testing.app.githubapi.TestGitHubApiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestGitHubApiModule::class])
interface TestApplicationComponent : ApplicationComponent {

    fun gitHubApi(): FakeGitHubApi

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): TestApplicationComponent
    }
}