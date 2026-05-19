package com.example.androidcodelabapp.data.repositories

import com.example.androidcodelabapp.data.network.responses.NetworkResponse
import com.example.androidcodelabapp.data.network.api.APIClient
import com.example.androidcodelabapp.data.network.api.APIService
import com.example.androidcodelabapp.data.network.dto.GithubUser
import com.example.androidcodelabapp.data.network.dto.GithubUsersResponse
import com.example.androidcodelabapp.domain.repositories.DeveloperRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeveloperRepositoryImpl : DeveloperRepository {

    private val apiClient: APIClient = APIService.apiClient

    override fun getDevelopers(
        onResult: (NetworkResponse<GithubUsersResponse>) -> Unit
    ) {
        try {
            apiClient.getAllDevelopers()
                .enqueue(object : Callback<GithubUsersResponse> {
                    override fun onResponse(
                        call: Call<GithubUsersResponse>,
                        response: Response<GithubUsersResponse>
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
                        call: Call<GithubUsersResponse>,
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
        onResult: (NetworkResponse<GithubUser>) -> Unit
    ) {
        try {
           apiClient.getDeveloperProfile(handle).enqueue(object : Callback<GithubUser> {
                override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        onResult(NetworkResponse(success = true, data = responseData))
                    } else {
                        val error = response.errorBody()?.string()
                        onResult(NetworkResponse(error = error, success = false))
                    }
                }

                override fun onFailure(call: Call<GithubUser>, t: Throwable) {
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