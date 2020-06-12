package com.github.paulajcm.ohmydog.android.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.paulajcm.ohmydog.android.model.domain.Dog
import com.github.paulajcm.ohmydog.android.model.dto.BreedDTO
import com.github.paulajcm.ohmydog.android.model.dto.asDomainModel
import com.github.paulajcm.ohmydog.android.network.DogService
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class DogRepository {

    val dogs = MutableLiveData<List<Dog>>()
    private val dogService = DogService.getInstance()

    fun refreshDogsLifeData(disposable: CompositeDisposable) {
        val dogArrayList = ArrayList<Dog>()
        disposable.add(loadDogs(object : DisposableObserver<Dog>() {
            override fun onNext(dog: Dog) {
                dogArrayList.add(dog)
                dogs.value = dogArrayList
            }

            override fun onError(e: Throwable) {
                Log.d("DOG", "DOGS error", e)
            }

            override fun onComplete() {

            }
        }))
    }

    private fun loadDogs(observer: DisposableObserver<Dog>): DisposableObserver<Dog> {
        return dogService.getAllBreeds()
            .flatMap { source: List<BreedDTO> ->
                Observable.fromIterable(source)
            }
            .concatMap { breedDTO: BreedDTO ->
                dogService.getDogByBreed(
                    breedDTO
                ).toObservable()
            }
            .map { it.asDomainModel() }
            .subscribeWith(observer)
    }

    companion object {
        private lateinit var INSTANCE: DogRepository

        fun getInstance(): DogRepository {
            synchronized(DogRepository::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = DogRepository()
                }
            }
            return INSTANCE
        }
    }
}