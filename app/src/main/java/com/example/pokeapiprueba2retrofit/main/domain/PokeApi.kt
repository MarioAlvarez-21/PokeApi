package com.example.pokeapiprueba2retrofit.main.domain

import com.example.pokeapiprueba2retrofit.main.domain.remote.PokemonResponse
import com.example.pokeapiprueba2retrofit.main.domain.model.pokemonform.PokemonFormModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    fun getPokemonsByLimitAndOffset(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<PokemonResponse>

    @GET("pokemon-form/{id}")
    fun getPokemonById(
        @Path("id") id: Int
    ): Call<PokemonFormModel>
}
