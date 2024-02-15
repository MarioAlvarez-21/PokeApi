package com.example.pokeapiprueba2retrofit.app.infopokemon.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapiprueba2retrofit.app.api.RetrofitModule
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.mapper.InfoAbilitiesMapper
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.mapper.InfoPokemonMapper
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.SpritesModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.InfoAbilitiesResponse
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.InfoPokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

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
    val tittleAbility1: MutableStateFlow<String?> = MutableStateFlow(null)
    val tittleAbility2: MutableStateFlow<String?> = MutableStateFlow(null)
    val tittleAbility3: MutableStateFlow<String?> = MutableStateFlow(null)
    val ability1: MutableStateFlow<String?> = MutableStateFlow(null)
    val ability2: MutableStateFlow<String?> = MutableStateFlow(null)
    val ability3: MutableStateFlow<String?> = MutableStateFlow(null)
    private val infoPokemonMapper = InfoPokemonMapper()
    private val infoAbilitiesMapper = InfoAbilitiesMapper()

    fun getPokemondata(id: Int) {
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
                        pokemonStatHP.value = pokemonModel!!.stats[0].baseStat
                        pokemonStatAttack.value = pokemonModel.stats[1].baseStat
                        pokemonStatDefense.value = pokemonModel.stats[2].baseStat
                        pokemonStatSpecialAttack.value = pokemonModel.stats[3].baseStat
                        pokemonStatSpecialDefense.value = pokemonModel.stats[4].baseStat
                        pokemonStatSpeed.value = pokemonModel.stats[5].baseStat
                        val abilitySize = pokemonModel.abilities.size
                        val a1 : String
                        val a2 : String
                        val a3 : String

                        when (abilitySize) {
                            1 -> {
                                a1 = pokemonModel.abilities.getOrNull(0)?.ability?.name ?: ""
                                tittleAbility1.value = a1
                                getPokemonAbility1(a1)
                            }
                            2 -> {
                                a1 = pokemonModel.abilities.getOrNull(0)?.ability?.name ?: ""
                                tittleAbility1.value = a1
                                getPokemonAbility1(a1)
                                a2 = pokemonModel.abilities.getOrNull(1)?.ability?.name ?: ""
                                tittleAbility2.value = a2
                                getPokemonAbility2(a2)
                            }
                            3 -> {
                                a1 = pokemonModel.abilities.getOrNull(0)?.ability?.name ?: ""
                                tittleAbility1.value = a1
                                getPokemonAbility1(a1)
                                a2 = pokemonModel.abilities.getOrNull(1)?.ability?.name ?: ""
                                tittleAbility2.value = a2
                                getPokemonAbility2(a2)
                                a3 = pokemonModel.abilities.getOrNull(2)?.ability?.name ?: ""
                                tittleAbility3.value = a3
                                getPokemonAbility3(a3)
                            }
                        }


                        Log.i("MARIO", pokemonModel.stats.toString())
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

    fun getPokemonAbility1(name: String) {
        viewModelScope.launch(Dispatchers.IO) {

            RetrofitModule.getInstance().getAbilityById(name).enqueue(object :
                Callback<InfoAbilitiesResponse> {
                override fun onResponse(
                    call: Call<InfoAbilitiesResponse>,
                    response: Response<InfoAbilitiesResponse>
                ) {
                    if (response.isSuccessful) {
                        val pokemonAbilityResponse = response.body()

                        val pokemonAbility =
                            pokemonAbilityResponse?.let { infoAbilitiesMapper.infoAbilitiesMapper(it) }

                        ability1.value = pokemonAbility?.effectEntries?.getOrNull(1)?.effect ?: ""


                        Log.i("MARIOoo", ability1.value.toString())
                    } else {
                        Log.i("MARIO", response.code().toString())
                    }

                }

                override fun onFailure(call: Call<InfoAbilitiesResponse>, t: Throwable) {

                }
            })
        }
    }

    fun getPokemonAbility2(name: String) {
        viewModelScope.launch(Dispatchers.IO) {

            RetrofitModule.getInstance().getAbilityById(name).enqueue(object :
                Callback<InfoAbilitiesResponse> {
                override fun onResponse(
                    call: Call<InfoAbilitiesResponse>,
                    response: Response<InfoAbilitiesResponse>
                ) {
                    if (response.isSuccessful) {
                        val pokemonAbilityResponse = response.body()

                        val pokemonAbility =
                            pokemonAbilityResponse?.let { infoAbilitiesMapper.infoAbilitiesMapper(it) }

                        ability2.value = pokemonAbility?.effectEntries?.getOrNull(1)?.effect ?: ""


                        Log.i("MARIOoo", ability1.value.toString())
                    } else {
                        Log.i("MARIO", response.code().toString())
                    }

                }

                override fun onFailure(call: Call<InfoAbilitiesResponse>, t: Throwable) {

                }
            })
        }
    }

    fun getPokemonAbility3(name: String) {
        viewModelScope.launch(Dispatchers.IO) {

            RetrofitModule.getInstance().getAbilityById(name).enqueue(object :
                Callback<InfoAbilitiesResponse> {
                override fun onResponse(
                    call: Call<InfoAbilitiesResponse>,
                    response: Response<InfoAbilitiesResponse>
                ) {
                    if (response.isSuccessful) {
                        val pokemonAbilityResponse = response.body()

                        val pokemonAbility =
                            pokemonAbilityResponse?.let { infoAbilitiesMapper.infoAbilitiesMapper(it) }

                        ability3.value = pokemonAbility?.effectEntries?.getOrNull(1)?.effect ?: ""


                        Log.i("MARIOoo", ability1.value.toString())
                    } else {
                        Log.i("MARIO", response.code().toString())
                    }

                }

                override fun onFailure(call: Call<InfoAbilitiesResponse>, t: Throwable) {

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