package com.kamira.githubusers.data.network.dto

import com.google.gson.annotations.SerializedName


data class GithubUserDto(
    @SerializedName("avatar_url")
    val profileImage: String? = null,

    @SerializedName("login")
    val userName: String? = null,

    @SerializedName("html_url")
    val profile: String? = null,

    @SerializedName("company")
    val organization: String? = null
)