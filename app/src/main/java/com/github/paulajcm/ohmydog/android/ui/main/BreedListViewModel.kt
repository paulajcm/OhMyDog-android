package com.github.paulajcm.ohmydog.android.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.paulajcm.ohmydog.android.repository.DogRepository
import io.reactivex.disposables.CompositeDisposable

class BreedListViewModel @ViewModelInject constructor(
    private val dogRepository: DogRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val disposable = CompositeDisposable()

    val dogs = dogRepository.dogs

    fun refreshBreeds() {
        dogRepository.refreshDogsLifeData(disposable)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
