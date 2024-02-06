package com.example.pokeapiprueba2retrofit.main.domain.model.pokemons

import com.example.pokeapiprueba2retrofit.main.domain.remote.PokemonsResultResponse

data class PokemonModel(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonsResultModel>
)
