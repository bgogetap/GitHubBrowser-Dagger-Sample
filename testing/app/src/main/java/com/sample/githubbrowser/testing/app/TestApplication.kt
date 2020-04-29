package com.sample.githubbrowser.testing.app

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import com.sample.githubbrowser.appdeps.ApplicationDeps
import com.sample.githubbrowser.appdeps.HasApplicationDeps

class TestApplication : Application(), HasApplicationDeps {

    companion object {
        val component: TestApplicationComponent
            get() =
                (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApplication).component
    }

    private lateinit var component: TestApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerTestApplicationComponent.factory().create(this)
    }

    override fun getApplicationDeps(): ApplicationDeps {
        return component
    }
}