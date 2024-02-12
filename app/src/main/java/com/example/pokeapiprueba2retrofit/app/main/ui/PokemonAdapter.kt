package com.example.pokeapiprueba2retrofit.app.main.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokeapiprueba2retrofit.R
import com.example.pokeapiprueba2retrofit.databinding.ItemPokemonBinding
import com.example.pokeapiprueba2retrofit.app.api.PokeApi
import com.example.pokeapiprueba2retrofit.app.infopokemon.ui.InfoPokemonActivity
import com.example.pokeapiprueba2retrofit.app.main.data.model.PokemonsResultModel

class PokemonAdapter(private val pokemons: MutableList<PokemonsResultModel>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon, position)

    }

    override fun getItemCount() = pokemons.size

    fun adicionarLista(pokemonList: MutableList<PokemonsResultModel>) {
        this.pokemons.addAll(pokemonList)
        notifyDataSetChanged()

    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemPokemonBinding.bind(itemView)
        val nameTextView: TextView = binding.textView
        val urlTextView: ImageView = binding.imageView

        fun bind(items: PokemonsResultModel, n: Int) {
            nameTextView.text = items.name

            val requestOptions = RequestOptions()
                .centerCrop()
                .error(android.R.drawable.ic_menu_gallery)
            Glide.with(urlTextView.context)
                .load(PokeApi.url(n))
                .apply(requestOptions)
                .fitCenter()
                .into(urlTextView)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, InfoPokemonActivity::class.java)
                intent.putExtra("id", n)
                itemView.context.startActivity(intent)
            }
        }

    }
}


