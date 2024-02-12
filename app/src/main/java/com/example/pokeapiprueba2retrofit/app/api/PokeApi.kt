package com.example.pokeapiprueba2retrofit.app.api

import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.InfoPokemonResponse
import com.example.pokeapiprueba2retrofit.app.main.data.remote.PokemonResponse
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

    companion object {
        fun url(n:Int):String{
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${n+1}.png"
        }
    }

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: Int): Call<InfoPokemonResponse>

}
