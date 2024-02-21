package com.example.pokeapiprueba2retrofit.app.infopokemon.data.mapper

import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.AbilityFlavorTextEntriesModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.AbilityLanguageModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.AbilityNamesModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.model.InfoAbilitiesModel
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.AbilityFlavorTextEntriesResponse
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.AbilityLanguageResponse
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.AbilityNamesResponse
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.InfoAbilitiesResponse

class InfoAbilitiesMapper {

    fun infoAbilitiesMapper(infoAbilitiesResponse: InfoAbilitiesResponse): InfoAbilitiesModel {
        val description = infoAbilitiesResponse.flavorTextEntries.map { descriptions ->
            abilitiesDescripcionMapper(descriptions)
        }
        val name = infoAbilitiesResponse.names.map { names ->
            abilitiesNameMapper(names)
        }

        return InfoAbilitiesModel(
            descripciones = description,
            names = name
        )
    }

    fun abilitiesDescripcionMapper(abilityFlavorTextEntriesResponse: AbilityFlavorTextEntriesResponse): AbilityFlavorTextEntriesModel {
        val languaje = languageDescriptionAndNameMapper(abilityFlavorTextEntriesResponse.language!!)
        return AbilityFlavorTextEntriesModel(
            description = abilityFlavorTextEntriesResponse.flavorText,
            language = languaje
        )
    }

    fun abilitiesNameMapper(abilityNamesResponse: AbilityNamesResponse): AbilityNamesModel {
        val languageModel = languageDescriptionAndNameMapper(abilityNamesResponse.language!!)
        return AbilityNamesModel(
            name = abilityNamesResponse.name,
            language = languageModel
        )
    }

    fun languageDescriptionAndNameMapper(abilityLanguageResponse: AbilityLanguageResponse): AbilityLanguageModel {
        return AbilityLanguageModel(
            name = abilityLanguageResponse.name
        )
    }
}