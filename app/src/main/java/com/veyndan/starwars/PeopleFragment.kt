package com.veyndan.starwars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_people.view.*

class PeopleFragment : Fragment() {

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_people, container, false)

        val adapter = PeopleAdapter()

        rootView.peopleRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        rootView.peopleRecyclerView.adapter = adapter

        val starWars = StarWars()

        disposables += rootView.searchBar.afterTextChangeEvents()
            .flatMap { textChangeEvent ->
                starWars.fetchPeople()
                    .andThen(starWars.people(searchQuery = textChangeEvent.editable.toString()))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { people ->
                adapter.people.clear()
                adapter.people.addAll(people)
                adapter.notifyDataSetChanged()
            }

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }
}
