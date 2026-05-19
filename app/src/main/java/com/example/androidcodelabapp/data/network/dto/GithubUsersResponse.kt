package com.example.androidcodelabapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class GithubUsersResponse(
    @SerializedName("items")
    val githubUsers: ArrayList<GithubUser> = arrayListOf()
)