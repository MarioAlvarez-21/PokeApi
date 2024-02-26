package com.example.pokeapiprueba2retrofit.app.main.data.mapper.error

import com.example.pokeapiprueba2retrofit.app.main.data.model.error.ErrorModel
import com.example.pokeapiprueba2retrofit.app.main.data.remote.error.ErrorResponse

class ErrorMapper : ResponseMapper<ErrorResponse, ErrorModel> {
    override fun fromResponse(response: ErrorResponse): ErrorModel {
        return ErrorModel(response.error ?: "", response.errorCode ?: "", response.message ?: "")
    }
}