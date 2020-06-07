package com.sample.githubbrowser.githubapi

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Module
object OkHttpConfigModule {

    @Provides
    @JvmStatic
    fun provideOkHttpConfigurator() = object : OkHttpConfigurator {
        override fun configure(clientBuilder: OkHttpClient.Builder) {
            clientBuilder.callTimeout(30, TimeUnit.SECONDS)
        }
    }
}