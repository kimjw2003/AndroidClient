package com.example.androidclient.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance: Retrofit? = Retrofit.Builder()
        .baseUrl("https://narsha2020.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance(): API {
        return instance!!.create(API::class.java)
    }
}