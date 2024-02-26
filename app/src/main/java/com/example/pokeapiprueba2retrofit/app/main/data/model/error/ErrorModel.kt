package com.example.pokeapiprueba2retrofit.app.main.data.model.error

import com.example.pokeapiprueba2retrofit.app.main.data.model.BaseModel

class ErrorModel(
    var error: String = "unknow",
    var errorCode: String = "",
    var message: String = "unknow"
) : BaseModel()