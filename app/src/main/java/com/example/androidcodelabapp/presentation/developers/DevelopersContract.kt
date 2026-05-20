package com.example.androidcodelabapp.presentation.developers;

import com.example.androidcodelabapp.data.network.dto.GithubUsersResponseDto;

interface DevelopersContract {
    fun showDevelopers(response: GithubUsersResponseDto)
    fun showError(s: String)
}
