package com.sample.githubbrowser.home

import com.sample.githubbrowser.appdeps.ApplicationDeps
import com.sample.githubbrowser.appdeps.applicationDeps
import com.sample.githubbrowser.di.component.getComponent
import com.sample.githubbrowser.di.scope.ScreenScope
import com.sample.githubbrowser.navigation.NavigationDeps
import com.sample.githubbrowser.navigation.navigationDeps
import dagger.Component

@ScreenScope
@Component(
    dependencies = [ApplicationDeps::class, NavigationDeps::class],
    modules = [HomeModule::class]
)
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    @Component.Factory
    interface Factory {

        fun create(applicationDeps: ApplicationDeps, navigationDeps: NavigationDeps): HomeComponent
    }
}

fun HomeFragment.inject() {
    getComponent {
        DaggerHomeComponent.factory()
            .create(requireContext().applicationDeps(), requireActivity().navigationDeps())
    }.inject(this)
}