package com.jxlopez.pokedex.data.network.Response

import com.google.gson.annotations.SerializedName
import com.jxlopez.pokedex.common.Constant

data class PokemonResponse (
    @SerializedName(Constant.EndPoints.Params.COUNT)
    var count: Int,
    @SerializedName(Constant.EndPoints.Params.NEXT)
    var next: String?,
    @SerializedName(Constant.EndPoints.Params.PREVIOUS)
    var previous: String?,
    @SerializedName(Constant.EndPoints.Params.RESULTS)
    var results: List<Results>? = null
)