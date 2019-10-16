package com.veyndan.starwars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class PeopleFragment : Fragment() {

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val peopleView = inflater.inflate(R.layout.fragment_people, container, false) as RecyclerView

        val adapter = PeopleAdapter()

        peopleView.layoutManager = LinearLayoutManager(requireContext())
        peopleView.adapter = adapter

        val starWars = StarWars()

        disposables += starWars.people()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { people ->
                adapter.people.clear()
                adapter.people.addAll(people)
                adapter.notifyDataSetChanged()
            }

        disposables += starWars.fetchPeople()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        return peopleView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }
}
