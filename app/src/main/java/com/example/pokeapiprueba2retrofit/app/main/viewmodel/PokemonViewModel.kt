package com.example.pokeapiprueba2retrofit.app.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapiprueba2retrofit.app.api.RetrofitModule.getInstance
import com.example.pokeapiprueba2retrofit.app.main.data.mapper.PokemonMapper
import com.example.pokeapiprueba2retrofit.app.main.data.model.PokemonsResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel() : ViewModel() {

    val pokemonsVM: MutableStateFlow<MutableList<PokemonsResultModel>?> = MutableStateFlow(null)
    val swipeRefresh: MutableStateFlow<Boolean?> = MutableStateFlow(true)
    private val maxNumberPokemons = 1026

    private val pokemonMapper = PokemonMapper()

    fun getPokemonsByLimitAndOffset(offset: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getInstance().getPokemonsByLimitAndOffset(maxNumberPokemons, offset)
                if (response.isSuccessful) {
                    val pokemonResponse = response.body()
                    val pokemonModel = pokemonResponse?.let { pokemonMapper.pokemonMapper(it) }

                    pokemonsVM.value = pokemonModel?.results?.toMutableList()

                } else {
                    Log.i("onResponse", response.toString())
                }
                swipeRefresh.value = false

            } catch (t: Throwable) {
                Log.i("onFailure", t.toString())
                Log.i("onFailure", "error al cargar pokemons")
            }
        }
    }
}