package com.sample.githubbrowser.testing.app.navigation

import com.sample.githubbrowser.navigation.Screen
import com.sample.githubbrowser.navigation.ScreenNavigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeScreenNavigator @Inject constructor() : ScreenNavigator {

    override fun goToScreen(screen: Screen) {

    }
}