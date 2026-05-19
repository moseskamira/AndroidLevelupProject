package com.example.androidcodelabapp.presenter

import com.example.androidcodelabapp.model.data.repositories.DeveloperRepositoryImpl
import com.example.androidcodelabapp.model.domain.entities.GithubUsersResponse
import com.example.androidcodelabapp.view.contracts.AllDevelopersContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DevelopersPresenter(private val repository: DeveloperRepositoryImpl) {

    fun getDevelopers(view: AllDevelopersContract) {
        val response = repository.getDevelopers()
        if (response.success) {
            val data = response.data
            if (data != null) {
                view.showDevelopers(data)
            }
        } else {
            val error = response.error
            if (error != null) {
                view.showError(error)
            }

        }
    }
}