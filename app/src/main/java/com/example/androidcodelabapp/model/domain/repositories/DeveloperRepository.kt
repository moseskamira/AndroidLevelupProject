package com.example.androidcodelabapp.model.domain.repositories

import com.example.androidcodelabapp.model.data.network.responses.NetworkResponse
import com.example.androidcodelabapp.model.domain.entities.GithubUser
import com.example.androidcodelabapp.model.domain.entities.GithubUsersResponse

interface DeveloperRepository {
    fun getDevelopers(): NetworkResponse<GithubUsersResponse>
    fun getDeveloperProfile(handle: String): NetworkResponse<GithubUser>

}