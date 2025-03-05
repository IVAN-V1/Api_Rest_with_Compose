package com.devtools.corutinas_with_compose.model

import com.google.gson.annotations.SerializedName

data class DogsResponse(

    /*
    son variables que vienen en el json de la APi
    [status]
    [message]
     */

    @SerializedName("status") var status: String,
    @SerializedName("message") val message: List<String>,
)


