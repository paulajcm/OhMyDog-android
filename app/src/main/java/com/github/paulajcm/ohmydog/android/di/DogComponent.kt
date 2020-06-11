package com.github.paulajcm.ohmydog.android.di

import com.github.paulajcm.ohmydog.android.network.DogService
import dagger.Component

@Component(modules = [DogModule::class])
interface DogComponent {
    fun inject(service: DogService)
}