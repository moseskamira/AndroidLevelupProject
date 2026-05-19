package com.example.androidcodelabapp.presenter

import com.example.androidcodelabapp.model.data.repositories.DeveloperRepositoryImpl
import com.example.androidcodelabapp.model.domain.entities.GithubUsersResponse
import com.example.androidcodelabapp.view.contracts.AllDevelopersContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DevelopersPresenter(private val repository: DeveloperRepositoryImpl) {

    fun getDevelopers(view: AllDevelopersContract) {
        repository.getDevelopers().enqueue(object : Callback<GithubUsersResponse> {
            override fun onResponse(
                call: Call<GithubUsersResponse>,
                response: Response<GithubUsersResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        view.showDevelopers(body)
                    } else {
                        view.showError("Empty response from server")
                    }
                } else {
                    view.showError("Request failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                view.showError("Request failed: ${t.message}")
            }
        })
    }
}