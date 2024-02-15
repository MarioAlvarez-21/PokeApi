package com.example.pokeapiprueba2retrofit.app.main.data.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonModel(
    val results: List<PokemonsResultModel>
) : BaseModel()
