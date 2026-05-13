package com.example.androidcodelabapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val apiClient: APIClient = retrofit.create(APIClient::class.java)

}
