package com.example.androidcodelabapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubService {
    private val baseUrl = "https://api.github.com/"
    private val retrofit:Retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()

    fun getRetrofit(): GithubAPI {
        return retrofit.create(GithubAPI::class.java)
    }
}
