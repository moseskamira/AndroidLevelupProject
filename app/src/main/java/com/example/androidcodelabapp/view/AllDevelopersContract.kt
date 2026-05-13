package com.example.androidcodelabapp.view;

import com.example.androidcodelabapp.model.GithubUsersResponse;

interface AllDevelopersContract {
    fun showDevelopers(response: GithubUsersResponse)
}
