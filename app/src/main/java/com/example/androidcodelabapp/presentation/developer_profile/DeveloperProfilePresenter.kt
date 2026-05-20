package com.example.androidcodelabapp.presentation.developer_profile

import com.example.androidcodelabapp.domain.repositories.DeveloperRepository


class DeveloperProfilePresenter(private val repository: DeveloperRepository) {
    fun getDeveloperProfile(handle: String, view: SingleDeveloperContract) {
        repository.getDeveloperProfile(handle) { response ->
            if (response.success) {
                val data = response.data
                data?.let {
                    view.showDeveloperProfile(data)
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
