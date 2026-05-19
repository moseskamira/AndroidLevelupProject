package com.example.androidcodelabapp.presentation.developers

import com.example.androidcodelabapp.data.repositories.DeveloperRepositoryImpl

class DevelopersPresenter(private val repository: DeveloperRepositoryImpl) {

    fun getDevelopers(view: DevelopersContract) {
        repository.getDevelopers{response->
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
}