package com.veyndan.starwars.api.model

import com.squareup.moshi.Json

internal data class PersonRaw(
    @Json(name = "birth_year") val birthYear: String,
    @Json(name = "eye_color") val eyeColor: String,
    val films: List<String>,
    val gender: String,
    @Json(name = "hair_color") val hairColor: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    @Json(name = "skin_color") val skinColor: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)
