package com.github.paulajcm.ohmydog.android.ui.main

import androidx.lifecycle.ViewModel
import com.github.paulajcm.ohmydog.android.repository.DogRepository
import io.reactivex.disposables.CompositeDisposable

class BreedListViewModel : ViewModel() {

    private val dogRepository = DogRepository.getInstance()
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
