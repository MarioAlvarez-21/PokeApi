package com.example.pokeapiprueba2retrofit.main.domain.mapper

import com.example.pokeapiprueba2retrofit.main.domain.model.pokemons.PokemonsResultModel
import com.example.pokeapiprueba2retrofit.main.domain.remote.PokemonResponse

class PokemonMapper(private val mapper: PokemonResultMapper) {

    fun toPokemonList(pokemonResponse: PokemonResponse): List<PokemonsResultModel> {

        return pokemonResponse.results.map { mapper.toPokemon(it) }
    }

}