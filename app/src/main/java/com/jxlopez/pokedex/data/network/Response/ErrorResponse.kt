package com.jxlopez.pokedex.data.network.Response

open class ErrorResponse {
    var statusCode: Int  = 0
    var throwable: Throwable? = null
}