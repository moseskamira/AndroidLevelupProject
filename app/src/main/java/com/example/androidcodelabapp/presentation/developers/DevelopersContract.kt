package com.example.androidcodelabapp.presentation.developers;

import com.example.androidcodelabapp.data.network.dto.GithubUsersResponse;

interface DevelopersContract {
    fun showDevelopers(response: GithubUsersResponse)
    fun showError(s: String)
}
