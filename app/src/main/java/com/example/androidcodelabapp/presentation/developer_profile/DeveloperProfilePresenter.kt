package com.example.androidcodelabapp.presentation.developer_profile

import com.example.androidcodelabapp.data.repositories.DeveloperRepositoryImpl

class DeveloperProfilePresenter(private val repository: DeveloperRepositoryImpl) {
    fun getDeveloperProfile(handle: String, view: SingleDeveloperContract) {
       repository.getDeveloperProfile(handle){response->
            if (response.success) {
                val data = response.data
                if (data != null) {
                    view.showDeveloperProfile(data)
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
