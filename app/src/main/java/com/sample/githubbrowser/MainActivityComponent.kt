package com.sample.githubbrowser

import com.sample.githubbrowser.di.component.getComponent
import com.sample.githubbrowser.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(): MainActivityComponent
    }
}

fun MainActivity.injectAndGetComponent(): MainActivityComponent {
    val component = getComponent { DaggerMainActivityComponent.create() }
    component.inject(this)
    return component
}