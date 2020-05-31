package com.sample.githubbrowser

import com.sample.githubbrowser.di.component.getComponent
import com.sample.githubbrowser.di.scope.ActivityScope
import com.sample.githubbrowser.navigation.NavigationDeps
import com.sample.githubbrowser.navigation.ScreenNavigator
import dagger.Binds
import dagger.Component
import dagger.Module

@ActivityScope
@Component(modules = [MainActivityModule::class])
interface MainActivityComponent : NavigationDeps {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(): MainActivityComponent
    }
}

@Module
interface MainActivityModule {

    @Binds
    fun bindScreenNavigator(activityDrivenScreenNavigator: ActivityDrivenScreenNavigator): ScreenNavigator
}

fun MainActivity.injectAndGetComponent(): MainActivityComponent {
    val component = getComponent { DaggerMainActivityComponent.create() }
    component.inject(this)
    return component
}