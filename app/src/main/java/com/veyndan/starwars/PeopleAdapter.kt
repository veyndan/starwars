package com.veyndan.starwars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.veyndan.starwars.model.Person
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_person.*

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    val people = mutableListOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val containerView = inflater.inflate(R.layout.item_person, parent, false)
        return ViewHolder(containerView)
    }

    override fun getItemCount(): Int = people.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = people[position]
        holder.bind(person)
    }

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var person: Person? = null

        init {
            containerView.setOnClickListener { view ->
                val action = PeopleFragmentDirections.profileAction(person!!.name, person!!.name)
                view.findNavController().navigate(action)
            }
        }

        fun bind(person: Person) {
            this.person = person

            name.text = person.name
        }
    }
}
