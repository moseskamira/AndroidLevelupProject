package com.example.androidcodelabapp.data.network.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @SerializedName("avatar_url")
    val profileImage: String? = null,

    @SerializedName("login")
    val userName: String? = null,

    @SerializedName("html_url")
    val profile: String? = null,

    @SerializedName("company")
    val organization: String? = null
) : Parcelable