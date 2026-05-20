package com.example.androidcodelabapp.presentation.developer_profile

import com.example.androidcodelabapp.data.network.dto.GithubUserDto

interface SingleDeveloperContract {
    fun showDeveloperProfile(profile: GithubUserDto)
    fun showError(s: String)
}
