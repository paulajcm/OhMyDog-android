package com.github.paulajcm.ohmydog.android.model.domain


data class Breed(
    val bredFor: String? = null,
    val breedGroup: String? = null,
    val heightDTO: Height,
    val id: Int,
    val lifeSpan: String? = null,
    val name: String,
    val origin: String? = null,
    var temperament: String? = null,
    var weight: Weight
)

data class Height(
    var imperial: String,
    var metric: String
)

data class Weight(
    var imperial: String,
    var metric: String
)