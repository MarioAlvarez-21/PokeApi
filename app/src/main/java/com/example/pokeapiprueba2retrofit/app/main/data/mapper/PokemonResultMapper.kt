package com.example.pokeapiprueba2retrofit.app.main.data.mapper

import com.example.pokeapiprueba2retrofit.app.main.data.model.PokemonsResultModel
import com.example.pokeapiprueba2retrofit.app.main.data.remote.PokemonsResultResponse

class PokemonResultMapper {

    companion object{
        fun pokemonResultoMapper(pokemonsResultResponse: PokemonsResultResponse): PokemonsResultModel {
            return PokemonsResultModel(name = pokemonsResultResponse.name, url = pokemonsResultResponse.url)
        }
    }

}