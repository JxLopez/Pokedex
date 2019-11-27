package com.jxlopez.pokedex.data.network.Response

import com.google.gson.annotations.SerializedName
import com.jxlopez.pokedex.common.Constant

data class Results (
    @SerializedName(Constant.EndPoints.Params.NAME)
    var name: String,
    @SerializedName(Constant.EndPoints.Params.URL)
    var url: String
)