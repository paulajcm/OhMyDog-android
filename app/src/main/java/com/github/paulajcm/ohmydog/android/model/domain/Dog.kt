package com.github.paulajcm.ohmydog.android.model.domain

data class Dog(
    val id: String,
    val height: Int,
    val width: Int,
    val url: String,
    var breed: Breed
)