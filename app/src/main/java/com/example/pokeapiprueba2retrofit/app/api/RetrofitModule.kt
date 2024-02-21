package com.example.pokeapiprueba2retrofit.app.api

import com.example.pokeapiprueba2retrofit.BuildConfig.POKEMON_API_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {

    private fun getRetrofit(): Retrofit {
        // Configuración del Logging Interceptor
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        // Cliente OkHttp con el interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(POKEMON_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client) // Se establece el cliente OkHttp con interceptor
            .build()
    }

    fun getInstance(): PokeApi {
        return getRetrofit().create(PokeApi::class.java)
    }

}