package com.veyndan.starwars

import org.junit.Test

class StarWarsTest {

    @Test
    fun something() {
        val starWars = StarWars()
        starWars.fetchPeople().subscribe()
        starWars.people().blockingForEach { println(it) }
    }
}
