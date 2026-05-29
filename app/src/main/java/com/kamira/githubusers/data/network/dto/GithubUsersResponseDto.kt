package com.kamira.githubusers.data.network.dto

import com.google.gson.annotations.SerializedName

data class GithubUsersResponseDto(
    @SerializedName("items")
    val githubUsers: ArrayList<GithubUserDto> = arrayListOf()
)