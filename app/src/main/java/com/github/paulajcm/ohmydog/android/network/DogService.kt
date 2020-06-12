package com.github.paulajcm.ohmydog.android.network

import com.github.paulajcm.ohmydog.android.di.DaggerDogComponent
import com.github.paulajcm.ohmydog.android.model.dto.BreedDTO
import com.github.paulajcm.ohmydog.android.model.dto.DogDTO
import com.github.paulajcm.ohmydog.android.model.dto.withBreed
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DogService {


    @Inject
    lateinit var dogApi: DogApi

    init {
        DaggerDogComponent.create().inject(this)
    }

    fun getAllBreeds(): Observable<List<BreedDTO>> {
        return dogApi.getAllBreeds()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDogByBreed(breedDTO: BreedDTO): Maybe<DogDTO> {
        return dogApi.getDogByBreedId("small", 1, breedDTO.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.isNotEmpty() }
            .flatMap { dogs: List<DogDTO> -> Maybe.just(dogs[0].withBreed(breedDTO)) }
    }

    companion object {
        private lateinit var INSTANCE: DogService

        fun getInstance(): DogService {
            synchronized(DogService::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = DogService()
                }
            }
            return INSTANCE
        }
    }

}