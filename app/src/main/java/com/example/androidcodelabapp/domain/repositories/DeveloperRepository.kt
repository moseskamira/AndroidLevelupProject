package com.example.androidcodelabapp.domain.repositories

import com.example.androidcodelabapp.data.network.responses.NetworkResponse
import com.example.androidcodelabapp.data.network.dto.GithubUserDto
import com.example.androidcodelabapp.data.network.dto.GithubUsersResponseDto

interface DeveloperRepository {
    fun getDevelopers(
        onResult: (NetworkResponse<GithubUsersResponseDto>) -> Unit
    )

    fun getDeveloperProfile(
        handle: String,
        onResult: (NetworkResponse<GithubUserDto>) -> Unit
    )
}