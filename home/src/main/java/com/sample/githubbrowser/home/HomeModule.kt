package com.sample.githubbrowser.home

import androidx.lifecycle.ViewModel
import com.sample.githubbrowser.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindViewModel(homeViewModel: HomeViewModel): ViewModel
}