package com.example.pokeapiprueba2retrofit.app.infopokemon.data.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoAbilitiesModel(
    val descripciones: List<AbilityFlavorTextEntriesModel>,
    val names: List<AbilityNamesModel>
) : BaseModelInfoPokemon()

@Parcelize
data class AbilityFlavorTextEntriesModel(
    val description: String?,
    val language: AbilityLanguageModel?
) : BaseModelInfoPokemon()

@Parcelize
data class AbilityNamesModel(
    val name: String?,
    val language: AbilityLanguageModel?
) : BaseModelInfoPokemon()

@Parcelize
data class AbilityLanguageModel(
    val name: String?
) : BaseModelInfoPokemon()




