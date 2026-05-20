package com.example.androidcodelabapp.data.network.api

import com.example.androidcodelabapp.data.network.dto.GithubUser
import com.example.androidcodelabapp.data.network.dto.GithubUsersResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIClient {
    @GET("search/users?q=type:User+location:Kampala+language:JAVA")
    fun getAllDevelopers(): Call<GithubUsersResponse>

    @GET("users/{githubUserName}")
    fun getDeveloperProfile(@Path("githubUserName") githubUserName: String): Call<GithubUser>

}
