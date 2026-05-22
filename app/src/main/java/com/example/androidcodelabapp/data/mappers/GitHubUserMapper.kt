package com.example.androidcodelabapp.data.mappers

import com.example.androidcodelabapp.data.network.dto.GithubUserDto
import com.example.androidcodelabapp.data.network.dto.GithubUsersResponseDto
import com.example.androidcodelabapp.domain.models.GitHubUser
import com.example.androidcodelabapp.domain.models.GitHubUserResponse

fun GithubUserDto.toDomain(): GitHubUser {
    return GitHubUser(
        profileImage = profileImage,
        userName = userName,
        profile = profile,
        organization = organization
    )
}

fun GithubUsersResponseDto.toDomain(): GitHubUserResponse {
    return GitHubUserResponse(
        githubUsers = ArrayList(
            githubUsers.map { user -> user.toDomain() }
        )
    )
}

