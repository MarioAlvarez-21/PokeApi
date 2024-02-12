package com.example.pokeapiprueba2retrofit.app.infopokemon.data.mapper

import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.Ability2Model
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.AbilityModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.InfoPokemonModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.SpritesModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.Type2Model
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.TypeModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.Ability2Response
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.AbilityResponse
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.InfoPokemonResponse
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.SpritesResponse
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.Type2Response
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.TypeResponse

class InfoPokemonMapper {

    fun infoPokemonMapper(infoPokemonResponse: InfoPokemonResponse): InfoPokemonModel {
        val spritesModel = spritesMapper(infoPokemonResponse.sprites)
        val typeModel = infoPokemonResponse.types.map { typeResponse ->
            typesMapper(typeResponse)
        }
        val abilityModel = infoPokemonResponse.abilities.map { abilityResponse ->
            abilitiesMapper(abilityResponse)
        }
        return InfoPokemonModel(
            id = infoPokemonResponse.id,
            name = infoPokemonResponse.name,
            sprites = spritesModel,
            weight = infoPokemonResponse.weight,
            height = infoPokemonResponse.height,
            types = typeModel,
            abilities = abilityModel

        )
    }

    fun spritesMapper(spritesResponse: SpritesResponse): SpritesModel {
        return SpritesModel(
            backDefault = spritesResponse.backDefault,
            backShiny = spritesResponse.backShiny,
            frontDefault = spritesResponse.frontDefault,
            frontShiny = spritesResponse.frontShiny
        )
    }

    fun typesMapper(typeResponse: TypeResponse): TypeModel {
        val typeModel = types2Mapper(typeResponse.type)
        return TypeModel(
            type = typeModel
        )
    }

    fun types2Mapper(type2Response: Type2Response): Type2Model {
        return Type2Model(name = type2Response.name)
    }

    fun abilitiesMapper(abilityResponse: AbilityResponse): AbilityModel {
        val abilityModel = abilities2Mapper(abilityResponse.ability)
        return AbilityModel(
            ability = abilityModel
        )
    }

    fun abilities2Mapper(ability2Response: Ability2Response): Ability2Model {
        return Ability2Model(name = ability2Response.name)
    }
}