package com.example.pokeapiprueba2retrofit.app.main.data.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonsResultModel(
    val name: String,
    val url: String
) : BaseModel()
