package com.kamira.githubusers.presentation.developers;

import com.kamira.githubusers.domain.models.GitHubUserResponse

interface DevelopersContract {
    fun showDevelopers(response: GitHubUserResponse)
    fun showError(s: String)
}
