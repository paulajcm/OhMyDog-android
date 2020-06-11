package com.github.paulajcm.ohmydog.android.model.dto

import com.github.paulajcm.ohmydog.android.model.domain.Breed
import com.github.paulajcm.ohmydog.android.model.domain.Height
import com.github.paulajcm.ohmydog.android.model.domain.Weight
import com.squareup.moshi.Json

data class BreedDTO(
    @Json(name = "bred_for")
    val bredFor: String? = null,

    @Json(name = "breed_group")
    val breedGroup: String? = null,

    @Json(name = "height")
    val height: HeightDTO,

    @Json(name = "id")
    val id: Int,

    @Json(name = "life_span")
    val lifeSpan: String? = null,

    @Json(name = "name")
    val name: String,

    @Json(name = "origin")
    val origin: String? = null,

    @Json(name = "temperament")
    var temperament: String? = null,

    @Json(name = "weight")
    var weight: WeightDTO
)

data class HeightDTO(
    @Json(name = "imperial")
    var imperial: String,

    @Json(name = "metric")
    var metric: String
)

data class WeightDTO(
    @Json(name = "imperial")
    var imperial: String,

    @Json(name = "metric")
    var metric: String
)


fun BreedDTO.asDomainModel(): Breed {
    return Breed(
        bredFor = bredFor,
        breedGroup = breedGroup,
        heightDTO = height.asDomainModel(),
        id = id,
        lifeSpan = lifeSpan,
        name = name,
        origin = origin,
        temperament = temperament,
        weight = weight.asDomainModel()
    )
}

private fun HeightDTO.asDomainModel(): Height {
    return Height(
        imperial = imperial,
        metric = metric
    )
}

private fun WeightDTO.asDomainModel(): Weight {
    return Weight(
        imperial = imperial,
        metric = metric
    )
}
