package com.example.androidcodelabapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubService {

    private var retrofit: Retrofit? = null

    fun getRetrofit(): GithubAPI {
        val BASE_URL = "https://api.github.com/"
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!.create(GithubAPI::class.java)

    }
}
