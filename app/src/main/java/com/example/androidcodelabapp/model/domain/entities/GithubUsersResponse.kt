package com.example.androidcodelabapp.model.domain.entities

import com.google.gson.annotations.SerializedName

data class GithubUsersResponse(
    @SerializedName("items")
    val githubUsers: ArrayList<GithubUser> = arrayListOf()
)