package com.kamira.githubusers.domain.repositories

import com.kamira.githubusers.data.network.responses.NetworkResponse
import com.kamira.githubusers.data.network.dto.GithubUserDto
import com.kamira.githubusers.domain.models.GitHubUserResponse

interface DeveloperRepository {
    fun getDevelopers(
        onResult: (NetworkResponse<GitHubUserResponse>) -> Unit
    )

    fun getDeveloperProfile(
        handle: String,
        onResult: (NetworkResponse<GithubUserDto>) -> Unit
    )
}