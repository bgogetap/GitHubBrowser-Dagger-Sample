package com.sample.githubbrowser

import com.sample.githubbrowser.di.scope.ActivityScope
import com.sample.githubbrowser.navigation.Screen
import com.sample.githubbrowser.navigation.ScreenNavigator
import javax.inject.Inject

@ActivityScope
class ActivityDrivenScreenNavigator @Inject constructor() : ScreenNavigator {

    var handleGoToScreen: ((Screen) -> Unit)? = null

    override fun goToScreen(screen: Screen) {
        handleGoToScreen?.invoke(screen)
    }
}