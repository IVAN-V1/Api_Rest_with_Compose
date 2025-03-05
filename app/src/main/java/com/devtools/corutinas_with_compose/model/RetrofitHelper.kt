package com.devtools.corutinas_with_compose.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {


    //Api
    private const val URL = "https://dog.ceo/api/breed/"


    //retrofit con json
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    fun intance():ApiServices{

        return retrofit.create(ApiServices::class.java)

    }


}


