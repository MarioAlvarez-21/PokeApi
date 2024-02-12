package com.example.pokeapiprueba2retrofit.app.main.data.remote

import com.google.gson.annotations.SerializedName

data class PokemonsResultResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)