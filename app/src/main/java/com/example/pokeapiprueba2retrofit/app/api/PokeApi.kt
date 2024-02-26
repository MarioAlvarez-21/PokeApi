package com.example.pokeapiprueba2retrofit.app.api

import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.InfoAbilitiesResponse
import com.example.pokeapiprueba2retrofit.app.infopokemon.data.remote.InfoPokemonResponse
import com.example.pokeapiprueba2retrofit.app.main.data.remote.pokemon.PokemonResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonsByLimitAndOffset(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonResponse>

    companion object {
        fun url(n:Int):String{
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${n}.png"
        }

        fun urlSound(n:Int):String{
            return "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/${n}.ogg"
        }
    }

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: Int): Call<InfoPokemonResponse>

    @GET("ability/{name}")
    fun getAbilityById(@Path("name") name: String): Call<InfoAbilitiesResponse>


}
