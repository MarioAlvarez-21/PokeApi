package com.example.pokeapiprueba2retrofit.app.infopokemon.data.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoAbilitiesModel(
    val effectEntries: List<AbilityEffectEntriesModel>,
    ) : BaseModelInfoPokemon()

@Parcelize
data class AbilityEffectEntriesModel(
    val effect: String,
    val shortEffect: String
): BaseModelInfoPokemon()
