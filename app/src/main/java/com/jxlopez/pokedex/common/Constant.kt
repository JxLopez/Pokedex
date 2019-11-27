package com.jxlopez.pokedex.common

object Constant {
    const val EMAIL = "jxlopez@domain.com"
    const val PASSWORD = "123456"
    const val COUNT_SPAN = 2

    object Preferences {
        const val LOGGED = "logged"
    }

    object EndPoints {
        const val GET_POKEMONS = "pokemon"

        object Header {
            const val LIMIT = "limit"
            const val OFFSET = "offset"
        }

        object Params {
            const val COUNT = "count"
            const val NEXT = "next"
            const val PREVIOUS = "previous"
            const val RESULTS = "results"
            const val NAME = "name"
            const val URL = "url"
        }
    }
}