package com.example.pokeapiprueba2retrofit.app.infopokemon.data.mapper

import com.example.pokeapiprueba2retrofit.app.api.PokeApi
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.AbilityEffectEntriesModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.InfoAbilitiesModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.AbilityEffectEntriesResponse
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.InfoAbilitiesResponse

class InfoAbilitiesMapper {

    fun infoAbilitiesMapper(infoAbilitiesResponse: InfoAbilitiesResponse): InfoAbilitiesModel {
        val effectEntries = infoAbilitiesResponse.effectEntries.map { abilities -> abilitiesMapper(abilities) }

        return InfoAbilitiesModel(effectEntries = effectEntries)
    }

    fun abilitiesMapper(abilityEffectEntriesResponse: AbilityEffectEntriesResponse): AbilityEffectEntriesModel {
        return AbilityEffectEntriesModel(
            effect = abilityEffectEntriesResponse.effect,
            shortEffect = abilityEffectEntriesResponse.shortEffect
        )
    }
}