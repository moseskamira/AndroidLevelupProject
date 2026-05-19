package com.example.androidcodelabapp.model.data.network.retrofit

import com.example.androidcodelabapp.model.domain.entities.GithubUser
import com.example.androidcodelabapp.model.domain.entities.GithubUsersResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIClient {
    @GET("search/users?q=type:User+location:Nairobi+language:JAVA")
    fun getAllDevelopers(): Call<GithubUsersResponse>

    @GET("users/{githubUserName}")
    fun getDeveloperProfile(@Path("githubUserName") githubUserName: String): Call<GithubUser>

}
