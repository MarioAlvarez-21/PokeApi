package com.example.pokeapiprueba2retrofit.app.infopokemon.data.model

import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.Ability2Response
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.AbilityResponse
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.Stat2Response
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.StatResponse
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.Type2Response
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.TypeResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoPokemonModel(
    val id: Int,
    val name: String,
    val sprites: SpritesModel?,
    val height: Int,
    val weight: Int,
    val types: List<TypeModel>,
    val abilities: List<AbilityModel>,
    val stats: List<StatModel>
) : BaseModelInfoPokemon()

@Parcelize
data class SpritesModel(
    val backDefault: String,
    val backShiny: String,
    val frontDefault: String,
    val frontShiny: String
) : BaseModelInfoPokemon()
@Parcelize
data class TypeModel(
    val type: Type2Model
) : BaseModelInfoPokemon()
@Parcelize
data class Type2Model(
    val name: String,
) : BaseModelInfoPokemon()

@Parcelize
data class AbilityModel(
    val ability: Ability2Model,
) : BaseModelInfoPokemon()

@Parcelize
data class Ability2Model(
    val name: String,
) : BaseModelInfoPokemon()

@Parcelize
data class StatModel(
    val baseStat: Int
) : BaseModelInfoPokemon()

