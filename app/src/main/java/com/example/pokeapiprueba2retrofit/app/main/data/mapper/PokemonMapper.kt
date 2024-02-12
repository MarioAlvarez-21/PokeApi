package com.example.pokeapiprueba2retrofit.app.main.data.mapper

import com.example.pokeapiprueba2retrofit.app.main.data.mapper.PokemonResultMapper.Companion.pokemonResultoMapper
import com.example.pokeapiprueba2retrofit.app.main.data.model.PokemonModel
import com.example.pokeapiprueba2retrofit.app.main.data.remote.PokemonResponse

class PokemonMapper {

    fun pokemonMapper(pokemonResponse: PokemonResponse): PokemonModel {
        val pokemonsResultModels = pokemonResponse.results.map {pokemonsResultResponse ->
            pokemonResultoMapper(pokemonsResultResponse)
        }
        return PokemonModel(results = pokemonsResultModels)
    }

}