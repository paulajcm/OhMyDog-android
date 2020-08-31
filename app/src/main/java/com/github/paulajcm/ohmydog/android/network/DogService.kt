package com.github.paulajcm.ohmydog.android.network

import com.github.paulajcm.ohmydog.android.model.dto.BreedDTO
import com.github.paulajcm.ohmydog.android.model.dto.DogDTO
import com.github.paulajcm.ohmydog.android.model.dto.withBreed
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogService @Inject constructor(
    var dogApi: DogApi
) {

    fun getAllBreeds(): Observable<List<BreedDTO>> {
        return dogApi.getAllBreeds()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDogByBreed(breedDTO: BreedDTO): Maybe<DogDTO> {
        return dogApi.getDogByBreedId("thumb", 1, breedDTO.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.isNotEmpty() }
            .flatMap { dogs: List<DogDTO> -> Maybe.just(dogs[0].withBreed(breedDTO)) }
    }
}