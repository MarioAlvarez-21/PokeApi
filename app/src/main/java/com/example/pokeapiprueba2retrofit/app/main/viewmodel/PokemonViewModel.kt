package com.example.pokeapiprueba2retrofit.app.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapiprueba2retrofit.app.main.data.mapper.PokemonMapper
import com.example.pokeapiprueba2retrofit.app.main.data.mapper.PokemonResultMapper
import com.example.pokeapiprueba2retrofit.app.main.data.model.PokemonsResultModel
import com.example.pokeapiprueba2retrofit.app.main.data.remote.PokemonResponse
import com.example.pokeapiprueba2retrofit.app.api.RetrofitModule.getInstance
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.SpritesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel() : ViewModel() {

    //val pokemonsVM: MutableLiveData<MutableList<PokemonsResultModel>> = MutableLiveData()
    val pokemonsVM: MutableStateFlow<MutableList<PokemonsResultModel>?> = MutableStateFlow(null)

    private val pokemonMapper = PokemonMapper()

    fun getPokemonsByLimitAndOffset(offset:Int) {
        viewModelScope.launch(Dispatchers.IO) {

                getInstance().getPokemonsByLimitAndOffset(60, offset).enqueue(object : Callback<PokemonResponse> {
                    override fun onResponse(
                        call: Call<PokemonResponse>,
                        response: Response<PokemonResponse>) {
                        if (response.isSuccessful) {
                            val pokemonResponse = response.body()
                            // Utiliza el mapeador para convertir la respuesta en el modelo de negocio
                            val pokemonModel = pokemonResponse?.let { pokemonMapper.pokemonMapper(it) }
                            // Actualiza el LiveData o el estado de tu ViewModel con el nuevo modelo
                            pokemonsVM.value = pokemonModel?.results?.toMutableList()
                        } else {

                        }
                    }

                    override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    }
                })
        }
    }
}