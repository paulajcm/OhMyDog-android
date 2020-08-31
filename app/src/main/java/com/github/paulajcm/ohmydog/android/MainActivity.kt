package com.github.paulajcm.ohmydog.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.paulajcm.ohmydog.android.ui.main.BreedListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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