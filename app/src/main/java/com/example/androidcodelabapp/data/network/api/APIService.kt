package com.example.androidcodelabapp.data.network.api

import com.example.androidcodelabapp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiClient: APIClient by lazy {
        retrofit.create(APIClient::class.java)
    }
}
