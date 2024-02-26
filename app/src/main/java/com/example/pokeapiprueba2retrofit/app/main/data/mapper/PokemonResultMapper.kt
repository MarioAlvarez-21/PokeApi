package com.example.pokeapiprueba2retrofit.app.main.data.mapper

import com.example.pokeapiprueba2retrofit.app.main.data.model.PokemonsResultModel
import com.example.pokeapiprueba2retrofit.app.main.data.remote.pokemon.PokemonsResultResponse

class PokemonResultMapper {

    companion object {
        fun pokemonResultoMapper(pokemonsResultResponse: PokemonsResultResponse): PokemonsResultModel {

            var id = -1
            var sprite = ""

            if (pokemonsResultResponse.url.isNotEmpty()) {
                id = pokemonsResultResponse.url.split("/")[6].toInt()
                sprite =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
            }


            return PokemonsResultModel(
                name = pokemonsResultResponse.name,
                url = pokemonsResultResponse.url,
                id = id,
                sprite = sprite
            )
        }
    }

}