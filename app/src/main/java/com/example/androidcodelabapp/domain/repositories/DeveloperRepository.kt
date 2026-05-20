package com.example.androidcodelabapp.domain.repositories

import com.example.androidcodelabapp.data.network.responses.NetworkResponse
import com.example.androidcodelabapp.data.network.dto.GithubUserDto
import com.example.androidcodelabapp.data.network.dto.GithubUsersResponseDto
import com.example.androidcodelabapp.domain.models.GitHubUserResponse

interface DeveloperRepository {
    fun getDevelopers(
        onResult: (NetworkResponse<GitHubUserResponse>) -> Unit
    )

    fun getDeveloperProfile(
        handle: String,
        onResult: (NetworkResponse<GithubUserDto>) -> Unit
    )
}