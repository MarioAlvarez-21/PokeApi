package com.example.pokeapiprueba2retrofit.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiprueba2retrofit.R
import com.example.pokeapiprueba2retrofit.databinding.ItemPokemonBinding
import com.example.pokeapiprueba2retrofit.main.domain.remote.PokemonsResultResponse
import com.squareup.picasso.Picasso

class PokemonAdapter(private val pokemons: List<PokemonsResultResponse>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemPokemonBinding.bind(itemView)
        val nameTextView: TextView = binding.textView
        val urlTextView: ImageView = binding.imageView

        fun bind(items: PokemonsResultResponse){
            nameTextView.text = items.name
            Picasso.get().load(items.url).fit().into(urlTextView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)

    }

    override fun getItemCount() = pokemons.size
}