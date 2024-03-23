package com.example.myapplication.data.sources.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory


class CatFactApi {
}


suspend fun getCatsFact() {

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

    val api = retrofit.create(CatsApi::class.java)

    val fact: CatFactResponse = api.getCatFact().await()
    Log.d("CatFactApi", "getCatsFact: $fact")
}

