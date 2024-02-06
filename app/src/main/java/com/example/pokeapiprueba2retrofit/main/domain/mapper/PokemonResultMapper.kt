package com.example.pokeapiprueba2retrofit.main.domain.mapper

import com.example.pokeapiprueba2retrofit.main.domain.model.pokemons.PokemonsResultModel
import com.example.pokeapiprueba2retrofit.main.domain.remote.PokemonsResultResponse

class PokemonResultMapper {

    fun toPokemon(pokemonsResultResponse: PokemonsResultResponse): PokemonsResultModel {
        return PokemonsResultModel(name = pokemonsResultResponse.name, url = pokemonsResultResponse.url)
    }
}