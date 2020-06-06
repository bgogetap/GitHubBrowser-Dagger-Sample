package com.sample.githubbrowser.home

import androidx.lifecycle.ViewModel
import com.sample.githubbrowser.di.viewmodel.ViewModelKey
import com.sample.githubbrowser.home.list.NumberItem
import com.sample.githubbrowser.home.list.NumberItemRenderer
import com.sample.githubbrowser.home.list.RepoItem
import com.sample.githubbrowser.home.list.RepoItemRenderer
import com.sample.githubbrowser.poweradapter.ItemRenderer
import com.sample.githubbrowser.poweradapter.RecyclerItem
import com.sample.githubbrowser.poweradapter.RendererKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @RendererKey(RepoItem::class)
    fun bindRepoItemRenderer(repoItemRenderer: RepoItemRenderer): ItemRenderer<out RecyclerItem>

    @Binds
    @IntoMap
    @RendererKey(NumberItem::class)
    fun bindNumberItemRenderer(numberItemRenderer: NumberItemRenderer): ItemRenderer<out RecyclerItem>
}