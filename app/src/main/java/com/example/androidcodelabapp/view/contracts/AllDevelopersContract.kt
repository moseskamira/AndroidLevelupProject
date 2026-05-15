package com.example.androidcodelabapp.view.contracts;

import com.example.androidcodelabapp.model.domain.entities.GithubUsersResponse;

interface AllDevelopersContract {
    fun showDevelopers(response: GithubUsersResponse)
}
