package com.veyndan.starwars.api.mapper

import com.veyndan.starwars.api.model.PeopleRaw
import com.veyndan.starwars.model.Person
import io.reactivex.functions.Function

internal object PeopleMapper : Function<PeopleRaw, List<Person>> {

    override fun apply(peopleRaw: PeopleRaw): List<Person> = peopleRaw.results
        .map { personRaw ->
            Person(
                birthYear = personRaw.birthYear,
                eyeColor = personRaw.eyeColor,
                films = personRaw.films,
                gender = personRaw.gender,
                hairColor = personRaw.hairColor,
                height = personRaw.height,
                homeworld = personRaw.homeworld,
                mass = personRaw.mass,
                name = personRaw.name,
                skinColor = personRaw.skinColor,
                species = personRaw.species,
                starships = personRaw.starships,
                vehicles = personRaw.vehicles
            )
        }
}
