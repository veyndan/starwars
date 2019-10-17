package com.veyndan.starwars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.os.ConfigurationCompat
import androidx.recyclerview.widget.RecyclerView
import com.veyndan.starwars.model.Person
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_profile.*

class ProfileAdapter(person: Person) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    data class Detail(@StringRes val title: Int, val information: String)

    private val details = listOf(
        Detail(R.string.birth_year, person.birthYear),
        Detail(R.string.eye_color, person.eyeColor),
        Detail(R.string.gender, person.gender),
        Detail(R.string.hair_color, person.hairColor),
        Detail(R.string.height, person.height),
        Detail(R.string.mass, person.mass)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val containerView = inflater.inflate(R.layout.item_profile, parent, false)
        return ViewHolder(containerView)
    }

    override fun getItemCount(): Int = details.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(details[position])
    }

    class ViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val currentLocale = ConfigurationCompat.getLocales(containerView.resources.configuration)[0]

        fun bind(detail: Detail) {
            title.setText(detail.title)
            information.text = detail.information.capitalize(currentLocale)
        }
    }
}
