package com.example.androidcodelabapp.view

import com.example.androidcodelabapp.model.GithubUser

interface SingleDeveloperContract {
    fun showDeveloperProfile(profile: GithubUser)
}
