package com.github.paulajcm.ohmydog.android.model.dto

import com.github.paulajcm.ohmydog.android.model.domain.Dog
import com.squareup.moshi.Json

data class DogDTO(
    @Json(name = "id")
    val id: String,

    @Json(name = "height")
    val height: Int,

    @Json(name = "width")
    val width: Int,

    @Json(name = "url")
    val url: String,

    @Json(name = "breed")
    var breed: BreedDTO
)

fun DogDTO.withBreed(breed: BreedDTO): DogDTO {
    this.breed = breed
    return this
}


fun DogDTO.asDomainModel(): Dog {
    return Dog(
        id = id,
        height = height,
        width = width,
        url = url,
        breed = breed.asDomainModel()
    )
}
