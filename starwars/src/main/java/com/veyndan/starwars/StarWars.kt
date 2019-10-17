package com.veyndan.starwars

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.veyndan.starwars.api.StarWarsService
import com.veyndan.starwars.api.mapper.PeopleMapper
import com.veyndan.starwars.model.Person
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.flatMapIterable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

// TODO Change to class and use Dagger to inject instances
object StarWars {

    // TODO Replace with database backed store
    private var store: List<Person>? = null

    private val subject = BehaviorSubject.create<List<Person>>()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://swapi.co/api/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val service = retrofit.create<StarWarsService>()

    private fun disk(): Maybe<List<Person>> = Maybe.fromCallable { store }

    private fun network(): Single<List<Person>> = service.people()
        .subscribeOn(Schedulers.io())
        .map(PeopleMapper)
        .doOnSuccess { store = it }

    fun person(personId: String): Observable<Person> = people()
        .flatMapIterable()
        .filter { it.name == personId }

    fun people(): Observable<List<Person>> = subject.share().startWith(emptyList<Person>())

    fun people(searchQuery: String): Observable<List<Person>> = subject.share()
        .map { people -> people.filter { it.name.contains(searchQuery, ignoreCase = true) } }

    fun fetchPeople(): Completable = Maybe.concat(disk(), network().toMaybe())
        .firstOrError()
        .doOnSuccess { subject.onNext(it) }
        .ignoreElement()
}
