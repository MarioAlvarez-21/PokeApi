package com.example.pokeapiprueba2retrofit.app.main.data.mapper.error

interface ResponseMapper<E, M> {
    fun fromResponse(response: E): M
}