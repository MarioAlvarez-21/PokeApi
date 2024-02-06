package com.example.pokeapiprueba2retrofit.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiprueba2retrofit.R
import com.example.pokeapiprueba2retrofit.databinding.ActivityMainBinding
import com.example.pokeapiprueba2retrofit.main.domain.remote.PokemonsResultResponse
import com.example.pokeapiprueba2retrofit.main.ui.viewmodel.PokemonViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]

        observer()

        binding.button.setOnClickListener {
            val et_limit = binding.etLimit.text.toString().toInt()
            val et_offset = binding.etOffset.text.toString().toInt()
            viewModel.getPokemonsByLimitAndOffset(et_limit, et_offset)
            viewModel.getPokemonsById(et_limit)
        }


    }

    private fun observer() {
        viewModel.pokemonsVM.observe(this) { pokemons ->
            recyclerConfigurations(pokemons)
        }
        viewModel.imagePokemon.observe(this) { imagePokemon ->
            if (imagePokemon != null) {
                Picasso.get().load(imagePokemon.front_default).fit().into(binding.ivPokemon)
            }
        }
    }

    private fun recyclerConfigurations(pokemons: List<PokemonsResultResponse>) {
        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PokemonAdapter(pokemons)
        recyclerView.adapter = adapter
    }


}