package com.example.androidcodelabapp.presenter

import android.util.Log

import com.example.androidcodelabapp.model.domain.entities.GithubUser
import com.example.androidcodelabapp.model.domain.entities.GithubUsersResponse
import com.example.androidcodelabapp.model.data.APIService
import com.example.androidcodelabapp.model.data.APIClient
import com.example.androidcodelabapp.view.contracts.AllDevelopersContract
import com.example.androidcodelabapp.view.contracts.SingleDeveloperContract

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubPresenter {
   private var apiInterface: APIClient = APIService.apiClient

    companion object {
        internal const val TAG = "Something Went Wrong!"
    }

    fun getDevelopers(view: AllDevelopersContract) {
        apiInterface.getAllDevelopers().enqueue(object : Callback<GithubUsersResponse> {
            override fun onResponse(call: Call<GithubUsersResponse>, response: Response<GithubUsersResponse>) {
                view.showDevelopers(response.body()!!)
            }

            override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                Log.e(TAG, "Something Went Wrong")
            }
        })
    }

    fun getDeveloperProfile(handle: String, view: SingleDeveloperContract) {
        apiInterface.getDeveloperProfile(handle).enqueue(object : Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                if(response.body()!== null){
                    view.showDeveloperProfile(response.body()!!)
                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                Log.e(TAG, "Something Went Wrong")
            }
        })
    }
}
