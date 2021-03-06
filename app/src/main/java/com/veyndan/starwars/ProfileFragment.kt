package com.veyndan.starwars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    private val args by navArgs<ProfileFragmentArgs>()

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        rootView.toolbar.title = args.personName
        rootView.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        rootView.detailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        disposables += StarWars.fetchPeople()
            .andThen(StarWars.person(args.personId))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { person ->
                rootView.detailsRecyclerView.adapter = ProfileAdapter(person)
            }

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }
}
