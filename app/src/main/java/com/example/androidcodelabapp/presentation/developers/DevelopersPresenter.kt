package com.example.androidcodelabapp.presentation.developers

import com.example.androidcodelabapp.domain.repositories.DeveloperRepository


class DevelopersPresenter(private val repository: DeveloperRepository) {
    fun getDevelopers(view: DevelopersContract) {
        repository.getDevelopers { response ->
            if (response.success) {
                val data = response.data
                data?.let {
                    view.showDevelopers(data)
                }
            } else {
                val error = response.error
                error?.let {
                    view.showError(error)
                }
            }

        }

    }
}