package com.example.androidcodelabapp.presentation.developer_profile

import com.example.androidcodelabapp.data.network.dto.GithubUser

interface SingleDeveloperContract {
    fun showDeveloperProfile(profile: GithubUser)
    fun showError(s: String)
}
