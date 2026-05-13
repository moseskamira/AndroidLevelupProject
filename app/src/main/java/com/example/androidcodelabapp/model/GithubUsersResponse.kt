package com.example.androidcodelabapp.model

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class GithubUsersResponse {
    @SerializedName("items")
    private var githubUsers: ArrayList<GithubUser> = ArrayList()

    fun getGithubUsers(): ArrayList<GithubUser> = githubUsers
}
