package com.example.myapplication.data.sources.api

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

interface CatFactApi {
    @GET("/facts/random?animal_type=cat&amount=1")
    suspend fun getCatFact(): CatFactResponse
}

class CatFactApiImpl @Inject constructor() : CatFactApi {

    override suspend fun getCatFact(): CatFactResponse {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create(CatFactApi::class.java)

        return api.getCatFact()
    }
}

data class CatFactResponse(
    @SerializedName("text")
    val text: String,
)


