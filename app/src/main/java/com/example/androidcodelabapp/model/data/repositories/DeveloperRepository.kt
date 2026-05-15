package com.example.androidcodelabapp.model.data.repositories

import com.example.androidcodelabapp.model.data.APIClient
import com.example.androidcodelabapp.model.data.APIService
import com.example.androidcodelabapp.model.domain.entities.GithubUser
import com.example.androidcodelabapp.model.domain.entities.GithubUsersResponse
import retrofit2.Call

class DeveloperRepository {

    private val apiClient: APIClient = APIService.apiClient

    fun getDevelopers(): Call<GithubUsersResponse> {
        return apiClient.getAllDevelopers()
    }

    fun getDeveloperProfile(handle: String): Call<GithubUser> {
        return apiClient.getDeveloperProfile(handle)
    }
}