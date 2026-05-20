package com.example.androidcodelabapp.data.repositories

import com.example.androidcodelabapp.data.network.responses.NetworkResponse
import com.example.androidcodelabapp.data.network.api.APIClient
import com.example.androidcodelabapp.data.network.api.APIService
import com.example.androidcodelabapp.data.network.dto.GithubUserDto
import com.example.androidcodelabapp.data.network.dto.GithubUsersResponseDto
import com.example.androidcodelabapp.domain.repositories.DeveloperRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeveloperRepositoryImpl : DeveloperRepository {

    private val apiClient: APIClient = APIService.apiClient

    override fun getDevelopers(
        onResult: (NetworkResponse<GithubUsersResponseDto>) -> Unit
    ) {
        try {
            apiClient.getAllDevelopers()
                .enqueue(object : Callback<GithubUsersResponseDto> {
                    override fun onResponse(
                        call: Call<GithubUsersResponseDto>,
                        response: Response<GithubUsersResponseDto>
                    ) {
                        if (response.isSuccessful) {
                            onResult(
                                NetworkResponse(
                                    data = response.body(),
                                    success = true
                                )
                            )

                        } else {
                            onResult(
                                NetworkResponse(
                                    error = response.errorBody()?.string(),
                                    success = false
                                )
                            )
                        }
                    }
                    override fun onFailure(
                        call: Call<GithubUsersResponseDto>,
                        t: Throwable
                    ) {
                        onResult(
                            NetworkResponse(
                                error = t.message,
                                success = false
                            )
                        )
                    }
                })

        } catch (e: Exception) {
            onResult(
                NetworkResponse(
                    error = e.message,
                    success = false
                )
            )
        }
    }

    override fun getDeveloperProfile(
        handle: String,
        onResult: (NetworkResponse<GithubUserDto>) -> Unit
    ) {
        try {
           apiClient.getDeveloperProfile(handle).enqueue(object : Callback<GithubUserDto> {
                override fun onResponse(call: Call<GithubUserDto>, response: Response<GithubUserDto>) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        onResult(NetworkResponse(success = true, data = responseData))
                    } else {
                        val error = response.errorBody()?.string()
                        onResult(NetworkResponse(error = error, success = false))
                    }
                }

                override fun onFailure(call: Call<GithubUserDto>, t: Throwable) {
                    val error = t.message
                    onResult(NetworkResponse(error = error, success = false))
                }
            })
        } catch (e: Exception) {
            val error = e.message
            onResult(NetworkResponse(error = error, success = false))


        }

    }
}