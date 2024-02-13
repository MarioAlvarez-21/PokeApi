package com.example.pokeapiprueba2retrofit.app.infopokemon.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapiprueba2retrofit.app.api.RetrofitModule
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.mapper.InfoPokemonMapper
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.SpritesModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.InfoPokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoPokemonViewModel : ViewModel() {

    val sprites1: MutableStateFlow<SpritesModel?> = MutableStateFlow(null)
    val pokemonName: MutableStateFlow<String?> = MutableStateFlow(null)
    val pokemonHeight: MutableStateFlow<Int?> = MutableStateFlow(null)
    val pokemonWeight: MutableStateFlow<Int?> = MutableStateFlow(null)
    val pokemonTypes: MutableStateFlow<List<String>?> = MutableStateFlow(null)
    val pokemonAbilities: MutableStateFlow<List<String>?> = MutableStateFlow(null)
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

                        val pokemonModel =
                            pokemonResponse?.let { infoPokemonMapper.infoPokemonMapper(it) }

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
                        Log.i("MARIO", response.code().toString())
                    }

                }

                override fun onFailure(call: Call<InfoPokemonResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    fun divFormat(param:Int, value:Double, concatenation:String):String{
        return String.format("%.1f", param.div(value)).plus(concatenation)
    }
    fun mulFormat(param:Int, value:Double, concatenation:String):String{
        return String.format("%.1f", param.times(value)).plus(concatenation)
    }
}