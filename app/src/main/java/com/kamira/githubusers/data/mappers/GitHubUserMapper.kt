package com.kamira.githubusers.data.mappers

import com.kamira.githubusers.data.network.dto.GithubUserDto
import com.kamira.githubusers.data.network.dto.GithubUsersResponseDto
import com.kamira.githubusers.domain.models.GitHubUser
import com.kamira.githubusers.domain.models.GitHubUserResponse

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

