package com.sample.githubbrowser.testing.app

import android.content.Context
import com.sample.githubbrowser.appcomponent.ApplicationComponent
import com.sample.githubbrowser.navigation.NavigationDeps
import com.sample.githubbrowser.testing.app.githubapi.FakeGitHubApi
import com.sample.githubbrowser.testing.app.githubapi.TestGitHubApiModule
import com.sample.githubbrowser.testing.app.navigation.TestNavigationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestGitHubApiModule::class, TestNavigationModule::class])
interface TestApplicationComponent : ApplicationComponent, NavigationDeps {

    fun gitHubApi(): FakeGitHubApi

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): TestApplicationComponent
    }
}