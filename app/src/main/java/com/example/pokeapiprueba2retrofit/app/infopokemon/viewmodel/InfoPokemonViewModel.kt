package com.example.pokeapiprueba2retrofit.app.infopokemon.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapiprueba2retrofit.app.api.RetrofitModule.getInstance
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.mapper.InfoAbilitiesMapper
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.mapper.InfoPokemonMapper
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.InfoAbilitiesModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.SpritesModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.InfoAbilitiesResponse
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
    val pokemonStatHP: MutableStateFlow<Int?> = MutableStateFlow(null)
    val pokemonStatAttack: MutableStateFlow<Int?> = MutableStateFlow(null)
    val pokemonStatDefense: MutableStateFlow<Int?> = MutableStateFlow(null)
    val pokemonStatSpecialAttack: MutableStateFlow<Int?> = MutableStateFlow(null)
    val pokemonStatSpecialDefense: MutableStateFlow<Int?> = MutableStateFlow(null)
    val pokemonStatSpeed: MutableStateFlow<Int?> = MutableStateFlow(null)
    val progress: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val listAbilities: MutableStateFlow<InfoAbilitiesModel?> = MutableStateFlow(null)
    private val infoPokemonMapper = InfoPokemonMapper()
    private val infoAbilitiesMapper = InfoAbilitiesMapper()

    fun getPokemondata(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            getInstance().getPokemonById(id).enqueue(object :
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
                        pokemonStatHP.value = pokemonModel!!.stats[0].baseStat
                        pokemonStatAttack.value = pokemonModel.stats[1].baseStat
                        pokemonStatDefense.value = pokemonModel.stats[2].baseStat
                        pokemonStatSpecialAttack.value = pokemonModel.stats[3].baseStat
                        pokemonStatSpecialDefense.value = pokemonModel.stats[4].baseStat
                        pokemonStatSpeed.value = pokemonModel.stats[5].baseStat

                        pokemonModel.abilities.forEach { nameAbility ->
                            getPokemonAbility1(
                                nameAbility.ability.name
                            )
                        }


                        progress.value = false
                    } else {
                        Log.i("MARIO", response.code().toString())
                    }

                }

                override fun onFailure(call: Call<InfoPokemonResponse>, t: Throwable) {
                    sprites1.value = SpritesModel("", "", "", "")
                    pokemonName.value = ""
                    pokemonHeight.value = 0
                    pokemonWeight.value = 0
                    pokemonTypes.value = emptyList()
                    pokemonAbilities.value = emptyList()
                    pokemonStatHP.value = 0
                    pokemonStatAttack.value = 0
                    pokemonStatDefense.value = 0
                    pokemonStatSpecialAttack.value = 0
                    pokemonStatSpecialDefense.value = 0
                    pokemonStatSpeed.value = 0
                }
            })
        }
    }


    fun getPokemonAbility1(name: String) {
        viewModelScope.launch(Dispatchers.IO) {

            getInstance().getAbilityById(name).enqueue(object :
                Callback<InfoAbilitiesResponse> {
                override fun onResponse(
                    call: Call<InfoAbilitiesResponse>,
                    response: Response<InfoAbilitiesResponse>
                ) {
                    if (response.isSuccessful) {
                        val pokemonAbilityResponse = response.body()

                        val pokemonAbility =
                            pokemonAbilityResponse?.let { infoAbilitiesMapper.infoAbilitiesMapper(it) }

                        listAbilities.value = pokemonAbility

                    } else {
                        Log.i("MARIO", response.code().toString())
                    }

                }

                override fun onFailure(call: Call<InfoAbilitiesResponse>, t: Throwable) {
                    Log.i("MARIOerr", t.message.toString())
                }
            })
        }
    }


    fun divFormat(param: Int, value: Double, concatenation: String): String {
        return String.format("%.1f", param.div(value)).plus(concatenation)
    }

    fun mulFormat(param: Int, value: Double, concatenation: String): String {
        return String.format("%.1f", param.times(value)).plus(concatenation)
    }


}