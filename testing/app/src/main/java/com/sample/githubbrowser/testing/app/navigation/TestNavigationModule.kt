package com.sample.githubbrowser.testing.app.navigation

import com.sample.githubbrowser.navigation.ScreenNavigator
import dagger.Binds
import dagger.Module

@Module
interface TestNavigationModule {

    @Binds
    fun bindScreenNavigator(fakeScreenNavigator: FakeScreenNavigator): ScreenNavigator
}