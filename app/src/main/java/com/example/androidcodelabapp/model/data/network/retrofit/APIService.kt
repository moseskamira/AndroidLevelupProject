package com.example.androidcodelabapp.model.data.network.retrofit

import com.example.androidcodelabapp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(Constants.BASEURL)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val apiClient: APIClient = retrofit.create(APIClient::class.java)

}
