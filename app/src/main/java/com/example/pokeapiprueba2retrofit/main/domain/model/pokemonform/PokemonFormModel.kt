package com.example.pokeapiprueba2retrofit.main.domain.model.pokemonform

data class PokemonFormModel(
    val form_name: String?,
    val form_names: List<Any>?,
    val form_order: Int?,
    val id: Int?,
    val is_battle_only: Boolean?,
    val is_default: Boolean?,
    val is_mega: Boolean?,
    val name: String?,
    val names: List<Any>?,
    val order: Int?,
    val pokemon: Pokemon?,
    val sprites: Sprites?,
    val types: List<Type>?,
    val version_group: VersionGroup?
)