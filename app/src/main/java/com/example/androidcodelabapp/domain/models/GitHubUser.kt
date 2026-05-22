package com.example.androidcodelabapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(
    val profileImage: String?,
    val userName: String?,
    val profile: String?,
    val organization: String?
) : Parcelable