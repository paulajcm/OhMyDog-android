package com.github.paulajcm.ohmydog.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.paulajcm.ohmydog.android.ui.main.BreedListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BreedListFragment.newInstance())
                    .commitNow()
        }
    }
}