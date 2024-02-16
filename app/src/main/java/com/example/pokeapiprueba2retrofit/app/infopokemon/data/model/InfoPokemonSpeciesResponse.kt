package com.example.pokeapiprueba2retrofit.app.infopokemon.data.model

import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.EvolvesFromSpeciesResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoPokemonSpeciesResponse(
    val evolvesFromSpecies: EvolvesFromSpeciesModel?
) : BaseModelInfoPokemon()

@Parcelize
data class EvolvesFromSpeciesModel(
    val name: String?,
) : BaseModelInfoPokemon()
