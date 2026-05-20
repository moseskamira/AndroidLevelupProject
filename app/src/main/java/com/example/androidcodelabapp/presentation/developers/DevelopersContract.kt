package com.example.androidcodelabapp.presentation.developers;

import com.example.androidcodelabapp.domain.models.GitHubUserResponse

interface DevelopersContract {
    fun showDevelopers(response: GitHubUserResponse)
    fun showError(s: String)
}
