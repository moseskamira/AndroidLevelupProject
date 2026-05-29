package com.kamira.githubusers.presentation.developer_profile

import com.kamira.githubusers.data.network.dto.GithubUserDto

interface SingleDeveloperContract {
    fun showDeveloperProfile(profile: GithubUserDto)
    fun showError(s: String)
}
