package com.example.pokeapiprueba2retrofit.main.retrofit

import com.example.pokeapiprueba2retrofit.BuildConfig.POKEMON_API_BASE_URL
import com.example.pokeapiprueba2retrofit.main.domain.PokeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(POKEMON_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): PokeApi {
        return getRetrofit().create(PokeApi::class.java)
    }


}