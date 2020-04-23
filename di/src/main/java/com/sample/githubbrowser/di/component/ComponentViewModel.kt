package com.sample.githubbrowser.di.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class ComponentViewModel : ViewModel() {

    var component: Any? = null

    override fun onCleared() {
        component = null
    }
}

fun <T> ViewModelStoreOwner.getComponent(createComponent: () -> T): T {
    val viewModel = ViewModelProvider(this)[ComponentViewModel::class.java]
    if (viewModel.component == null) {
        viewModel.component = createComponent()
    }

    @Suppress("UNCHECKED_CAST")
    return viewModel.component as T
}