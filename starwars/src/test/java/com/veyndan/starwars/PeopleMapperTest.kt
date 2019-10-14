package com.veyndan.starwars

import com.veyndan.starwars.api.mapper.PeopleMapper
import com.veyndan.starwars.api.model.PeopleRaw
import com.veyndan.starwars.api.model.PersonRaw
import com.veyndan.starwars.model.Person
import org.junit.Assert
import org.junit.Test

class PeopleMapperTest {

    @Test
    fun `Correct mapping from raw response`() {
        val expected = listOf(
            Person(
                birthYear = "19BBY",
                eyeColor = "blue",
                films = listOf(
                    "https://swapi.co/api/films/2/",
                    "https://swapi.co/api/films/6/",
                    "https://swapi.co/api/films/3/",
                    "https://swapi.co/api/films/1/",
                    "https://swapi.co/api/films/7/"
                ),
                gender = "male",
                hairColor = "blond",
                height = "172",
                homeworld = "https://swapi.co/api/planets/1/",
                mass = "77",
                name = "Luke Skywalker",
                skinColor = "fair",
                species = listOf("https://swapi.co/api/species/1/"),
                starships = listOf(
                    "https://swapi.co/api/starships/12/",
                    "https://swapi.co/api/starships/22/"
                ),
                url = "https://swapi.co/api/people/1/",
                vehicles = listOf(
                    "https://swapi.co/api/vehicles/14/",
                    "https://swapi.co/api/vehicles/30/]"
                )
            )
        )

        val peopleRaw = PeopleRaw(
            listOf(
                PersonRaw(
                    birthYear = "19BBY",
                    eyeColor = "blue",
                    films = listOf(
                        "https://swapi.co/api/films/2/",
                        "https://swapi.co/api/films/6/",
                        "https://swapi.co/api/films/3/",
                        "https://swapi.co/api/films/1/",
                        "https://swapi.co/api/films/7/"
                    ),
                    gender = "male",
                    hairColor = "blond",
                    height = "172",
                    homeworld = "https://swapi.co/api/planets/1/",
                    mass = "77",
                    name = "Luke Skywalker",
                    skinColor = "fair",
                    species = listOf("https://swapi.co/api/species/1/"),
                    starships = listOf(
                        "https://swapi.co/api/starships/12/",
                        "https://swapi.co/api/starships/22/"
                    ),
                    url = "https://swapi.co/api/people/1/",
                    vehicles = listOf(
                        "https://swapi.co/api/vehicles/14/",
                        "https://swapi.co/api/vehicles/30/]"
                    )
                )
            )
        )

        val actual = PeopleMapper.apply(peopleRaw)

        Assert.assertEquals(expected, actual)
    }
}
