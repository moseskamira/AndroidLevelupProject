package com.example.androidcodelabapp.model.data.repositories

import com.example.androidcodelabapp.model.data.network.responses.NetworkResponse
import com.example.androidcodelabapp.model.data.network.retrofit.APIClient
import com.example.androidcodelabapp.model.data.network.retrofit.APIService
import com.example.androidcodelabapp.model.domain.entities.GithubUser
import com.example.androidcodelabapp.model.domain.entities.GithubUsersResponse
import com.example.androidcodelabapp.model.domain.repositories.DeveloperRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeveloperRepositoryImpl: DeveloperRepository {

    private val apiClient: APIClient = APIService.apiClient

    override fun getDevelopers(): NetworkResponse<GithubUsersResponse> {
        try {
            val callResponse = apiClient.getAllDevelopers()
            lateinit var networkResponse: NetworkResponse<GithubUsersResponse>
            callResponse.enqueue(object : Callback<GithubUsersResponse>{
                override fun onResponse(
                    call: Call<GithubUsersResponse>,
                    response: Response<GithubUsersResponse>
                ) {
                    if(response.isSuccessful){
                        val responseData = response.body()
                        networkResponse = NetworkResponse(data = responseData, success = true)
                    }else{
                        val error = response.errorBody()?.string()
                        networkResponse = NetworkResponse(error = error, success = false)

                    }
                }
                override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                    val error = t.message
                    networkResponse = NetworkResponse(error = error, success = false)
                }

            })
            return  networkResponse
        }catch (e:Exception){
            val error = e.message
            return NetworkResponse(error = error, success = false, data = null)


        }

    }

    override fun getDeveloperProfile(handle: String):NetworkResponse<GithubUser> {
        return apiClient.getDeveloperProfile(handle)
    }
}