package com.sample.githubbrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.githubbrowser.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var component: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        component = injectAndGetComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.screen_container, HomeFragment())
                .commit()
        }
    }
}
