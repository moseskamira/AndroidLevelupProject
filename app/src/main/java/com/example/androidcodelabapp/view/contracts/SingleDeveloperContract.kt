package com.example.androidcodelabapp.view.contracts

import com.example.androidcodelabapp.model.domain.entities.GithubUser

interface SingleDeveloperContract {
    fun showDeveloperProfile(profile: GithubUser)
}
