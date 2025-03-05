package com.devtools.corutinas_with_compose.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiServices {

    //peticion get
    @GET
    suspend fun getDogsByBreeds(@Url url:String):Response<DogsResponse>



}

