package com.example.androidcodelabapp.model

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class GithubUsersResponse {
    @SerializedName("items")
    private var githubUsers: ArrayList<GithubUsers> = ArrayList()

    fun getGithubUsers(): ArrayList<GithubUsers> = githubUsers
}
