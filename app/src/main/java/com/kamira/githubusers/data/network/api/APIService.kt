package com.kamira.githubusers.data.network.api

import com.kamira.githubusers.util.Constants
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
