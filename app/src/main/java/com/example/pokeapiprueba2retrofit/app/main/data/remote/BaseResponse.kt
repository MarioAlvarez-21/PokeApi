package com.example.pokeapiprueba2retrofit.app.main.data.remote

import com.example.pokeapiprueba2retrofit.app.main.data.model.error.ErrorModel

sealed class BaseResponse<T> {
    class Success<T>(val data: T) : BaseResponse<T>()
    class Error<T>(val error: ErrorModel) : BaseResponse<T>()
}