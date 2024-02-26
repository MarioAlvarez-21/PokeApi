package com.example.pokeapiprueba2retrofit.app.main.data.remote

import com.example.pokeapiprueba2retrofit.BuildConfig
import com.example.pokeapiprueba2retrofit.app.main.data.model.error.ErrorModel
import com.example.pokeapiprueba2retrofit.app.main.data.remote.error.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseCallService {

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): BaseResponse<T> {
        val response: Response<T>
        try {
            response = call.invoke()

//            return BaseResponse.Error(ErrorModel(error = "Error forzado", errorCode = "F", message = "Error forzado"))

            return if (!response.isSuccessful) {
                val errorResponse = mapErrorResponse(response)
                //Log.e(TAG, "l> errorResponse: ${errorResponse.message}")
                BaseResponse.Error(errorResponse)
            } else {
                response.body()?.let { body ->
                    BaseResponse.Success(body)
                } ?: BaseResponse.Error(mapErrorResponse(response))
            }
        } catch (throwable: Throwable) {
            //Log.e(TAG, "l> throwable: ${throwable.message}")
            throwable.printStackTrace()
            return BaseResponse.Error(mapErrorResponse(throwable))
        }
    }

    private fun <T> mapErrorResponse(response: Response<T>): ErrorModel {
        val errorBody = response.errorBody()?.string()
        val errorData = try {
            val parsedData = Gson().fromJson(errorBody, ErrorResponse::class.java)
            if (response.code() == 401) {
                parsedData.errorCode = 401.toString()
            }
            parsedData
        } catch (exception: java.lang.Exception) {
            //Log.e(TAG, "l> exception: ${exception.message}")
            exception.printStackTrace()
            null
        }

        return ErrorModel(
            errorData?.error ?: "",
            errorData?.errorCode ?: "0",
            errorData?.message ?: ""
        )
    }

    private fun mapErrorResponse(throwable: Throwable): ErrorModel {
        return if (BuildConfig.DEBUG) {
            ErrorModel(
                "UNKNOW mapErrorResponse",
                "UNKNOW mapErrorResponse",
                throwable.message ?: "UNKNOW mapErrorResponse"
            )
        } else {
            ErrorModel(
                "Lo sentimos, estamos presentando problemas de conexión.",
                "0",
                "Vuelve a intentarlo más tarde."
            )
        }
    }
}