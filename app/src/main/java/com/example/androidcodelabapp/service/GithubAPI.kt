package com.example.androidcodelabapp.service

import com.example.androidcodelabapp.model.GithubUsers
import com.example.androidcodelabapp.model.GithubUsersResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {
    @GET("search/users?q=type:User+location:Nairobi+language:JAVA")
    fun getAllDevelopers(): Call<GithubUsersResponse>

    @GET("users/{githubUserName}")
    fun getDeveloperProfile(@Path("githubUserName") githubUserName: String): Call<GithubUsers>

}
