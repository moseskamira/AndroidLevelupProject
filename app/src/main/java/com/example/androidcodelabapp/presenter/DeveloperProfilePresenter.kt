package com.example.androidcodelabapp.presenter

import com.example.androidcodelabapp.model.data.repositories.DeveloperRepositoryImpl
import com.example.androidcodelabapp.model.domain.entities.GithubUser
import com.example.androidcodelabapp.view.contracts.SingleDeveloperContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeveloperProfilePresenter(private val repository: DeveloperRepositoryImpl) {
    fun getDeveloperProfile(handle: String, view: SingleDeveloperContract) {
        repository.getDeveloperProfile(handle).enqueue(object : Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                if (response.body() !== null) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            view.showDeveloperProfile(body)
                        } else {
                            view.showError("Empty profile data")
                        }

                    } else {
                        view.showError("Error: ${response.code()}")
                    }
                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                view.showError("Error: ${t.message}")
            }
        })
    }
}
