package com.github.paulajcm.ohmydog.android.network

import com.github.paulajcm.ohmydog.android.model.dto.BreedDTO
import com.github.paulajcm.ohmydog.android.model.dto.DogDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi {
    @GET("/v1/breeds")
    fun getAllBreeds(): Single<List<BreedDTO>>

    @GET("/v1/images/search")
    fun getDogByBreedId(
        @Query("size") size: String?,
        @Query("limit") limit: Int,
        @Query("breed_id") breedId: Int
    ): Single<List<DogDTO>>
}