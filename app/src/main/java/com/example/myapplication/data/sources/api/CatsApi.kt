package com.example.myapplication.data.sources.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

interface CatsApi {

    @GET("/facts/random?animal_type=cat&amount=1")
    fun getCatFact(): Call<CatFactResponse>


}

data class CatFactResponse(

    @SerializedName("text")
    val text: String,

)





