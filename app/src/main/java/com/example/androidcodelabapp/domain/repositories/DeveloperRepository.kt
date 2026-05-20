package com.example.androidcodelabapp.domain.repositories

import com.example.androidcodelabapp.data.network.responses.NetworkResponse
import com.example.androidcodelabapp.data.network.dto.GithubUser
import com.example.androidcodelabapp.data.network.dto.GithubUsersResponse

interface DeveloperRepository {
    fun getDevelopers(
        onResult: (NetworkResponse<GithubUsersResponse>) -> Unit
    )

    fun getDeveloperProfile(
        handle: String,
        onResult: (NetworkResponse<GithubUser>) -> Unit
    )
}