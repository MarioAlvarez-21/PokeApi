package com.example.pokeapiprueba2retrofit.app.infopokemon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapiprueba2retrofit.app.api.RetrofitModule
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.mapper.InfoPokemonMapper
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.SpritesModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.TypeModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.InfoPokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoPokemonViewModel : ViewModel() {

    val sprites1: MutableLiveData<SpritesModel?> = MutableLiveData()
    val pokemonName: MutableLiveData<String?> = MutableLiveData()
    val pokemonHeight: MutableLiveData<Int?> = MutableLiveData()
    val pokemonWeight: MutableLiveData<Int?> = MutableLiveData()
    val pokemonTypes: MutableLiveData<List<String>?> = MutableLiveData()
    val pokemonAbilities: MutableLiveData<List<String>?> = MutableLiveData()
    private val infoPokemonMapper = InfoPokemonMapper()

    fun getSprites(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            RetrofitModule.getInstance().getPokemonById(id).enqueue(object :
                Callback<InfoPokemonResponse> {
                override fun onResponse(
                    call: Call<InfoPokemonResponse>,
                    response: Response<InfoPokemonResponse>
                ) {
                    if (response.isSuccessful) {
                        val pokemonResponse = response.body()
                        // Utiliza el mapeador para convertir la respuesta en el modelo de negocio
                        val pokemonModel = pokemonResponse?.let { infoPokemonMapper.infoPokemonMapper(it) }
                        // Actualiza el LiveData o el estado de tu ViewModel con el nuevo modelo
                        sprites1.value = pokemonModel?.sprites
                        pokemonName.value = pokemonModel?.name
                        pokemonHeight.value = pokemonModel?.height
                        pokemonWeight.value = pokemonModel?.weight
                        val typeNames = pokemonModel?.types?.map { it.type.name }
                        pokemonTypes.value = typeNames
                        val abilitiesNames = pokemonModel?.abilities?.map { it.ability.name }
                        pokemonAbilities.value = abilitiesNames

                        Log.i("MARIO", pokemonModel?.types.toString())
                    } else {
                        // Maneja la respuesta de error
                    }

                }

                override fun onFailure(call: Call<InfoPokemonResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}