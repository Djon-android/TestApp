package com.example.testapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.testapp.R
import com.example.testapp.databinding.CharacterItemBinding
import com.example.testapp.domain.Character
import com.squareup.picasso.Picasso

class CharacterAdapter(
    private val context: Context
) : ListAdapter<Character, CharacterViewHolder>(CharacterDiffCallback) {

    var onReachEndListener: OnReachEndListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        if (currentList.size >= 20 && position > currentList.size - 4) {
            onReachEndListener?.onReachEnd()
        }
        val character = getItem(position)
        with(holder.binding) {
            val speciesGenderTemplate =
                context.resources.getString(R.string.species_gender_template)
            Picasso.get().load(character.image).into(ivCharacter)
            tvNameCharacter.text = character.name
            tvSpeciesGender.text =
                String.format(speciesGenderTemplate, character.species, character.gender)
            tvNameLocation.text = character.location.nameLocation
            tvStatus.text = character.status
            when (character.status) {
                STATUS_ALIVE -> {
                    tvStatus.setTextColor(context.resources.getColor(R.color.status_tc_alive))
                    tvStatus.setBackgroundResource(R.drawable.bg_status_alive)
                }
                STATUS_DEAD -> {
                    tvStatus.setTextColor(context.resources.getColor(R.color.status_tc_dead))
                    tvStatus.setBackgroundResource(R.drawable.bg_status_dead)
                }
                else -> {
                    tvStatus.setTextColor(context.resources.getColor(R.color.status_tc_unknown))
                    tvStatus.setBackgroundResource(R.drawable.bg_status_unknown)
                }
            }
        }
    }

    interface OnReachEndListener {
        fun onReachEnd()
    }

    companion object {
        private const val STATUS_ALIVE = "Alive"
        private const val STATUS_DEAD = "Dead"
    }
}