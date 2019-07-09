package com.example.androidcodelabapp.presenter

import android.util.Log

import com.example.androidcodelabapp.model.GithubUsers
import com.example.androidcodelabapp.model.GithubUsersResponse
import com.example.androidcodelabapp.service.GithubService
import com.example.androidcodelabapp.service.GithubAPI
import com.example.androidcodelabapp.view.AllDevelopersView
import com.example.androidcodelabapp.view.SingleDeveloperView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubPresenter {
   private var apiInterface: GithubAPI = GithubService().getRetrofit()

    companion object {
        internal const val TAG = "Something Went Wrong!"
    }

    fun getDevelopers(allDevelopersView: AllDevelopersView) {
        apiInterface.getAllDevelopers().enqueue(object : Callback<GithubUsersResponse> {
            override fun onResponse(call: Call<GithubUsersResponse>, response: Response<GithubUsersResponse>) {
                allDevelopersView.showDevelopers(response.body()!!)
            }

            override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                Log.e(TAG, "Something Went Wrong")
            }
        })
    }

    fun getDeveloperProfile(handle: String, singleDeveloperView: SingleDeveloperView) {
        apiInterface.getDeveloperProfile(handle).enqueue(object : Callback<GithubUsers> {
            override fun onResponse(call: Call<GithubUsers>, response: Response<GithubUsers>) {
                if(response.body()!== null){
                    singleDeveloperView.showDeveloperProfile(response.body()!!)
                }
            }

            override fun onFailure(call: Call<GithubUsers>, t: Throwable) {
                Log.e(TAG, "Something Went Wrong")
            }
        })
    }
}
