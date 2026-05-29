package com.kamira.githubusers.data.network.api

import com.kamira.githubusers.data.network.dto.GithubUserDto
import com.kamira.githubusers.data.network.dto.GithubUsersResponseDto

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIClient {
    @GET("search/users?q=type:User+location:Kampala+language:JAVA")
    fun getAllDevelopers(): Call<GithubUsersResponseDto>

    @GET("users/{githubUserName}")
    fun getDeveloperProfile(@Path("githubUserName") githubUserName: String): Call<GithubUserDto>

}
