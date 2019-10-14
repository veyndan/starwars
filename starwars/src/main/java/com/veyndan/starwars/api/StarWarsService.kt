package com.veyndan.starwars.api

import com.veyndan.starwars.api.model.PeopleRaw
import io.reactivex.Single
import retrofit2.http.GET

internal interface StarWarsService {

    @GET("people")
    fun people(): Single<PeopleRaw>
}
