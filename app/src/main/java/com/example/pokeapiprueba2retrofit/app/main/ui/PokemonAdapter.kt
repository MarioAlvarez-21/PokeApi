package com.example.pokeapiprueba2retrofit.app.main.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokeapiprueba2retrofit.R
import com.example.pokeapiprueba2retrofit.app.api.PokeApi
import com.example.pokeapiprueba2retrofit.app.infopokemon.ui.InfoPokemonActivity
import com.example.pokeapiprueba2retrofit.app.main.data.model.PokemonsResultModel
import com.example.pokeapiprueba2retrofit.databinding.ItemPokemonBinding

class PokemonAdapter(private var pokemons: MutableList<PokemonsResultModel>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {


    // Método para actualizar la lista de Pokemon
    fun updatePokemonList(newPokemonList: MutableList<PokemonsResultModel>) {
        pokemons = newPokemonList
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado
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

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemPokemonBinding.bind(itemView)
        val nameTextView: TextView = binding.textView
        val urlTextView: ImageView = binding.imageView
        val indexTextView: TextView = binding.tvIndex

        fun bind(items: PokemonsResultModel) {
            val id = items.id
            nameTextView.text = items.name
            indexTextView.text = id.toString()


            val requestOptions = RequestOptions()
                .centerCrop()
                .error(R.drawable.incognita)
            Glide.with(urlTextView.context)
                .load(PokeApi.url(id))
                .apply(requestOptions)
                .fitCenter()
                .into(urlTextView)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, InfoPokemonActivity::class.java)
                intent.putExtra("id", id)
                itemView.context.startActivity(intent)
            }
        }

    }


}


