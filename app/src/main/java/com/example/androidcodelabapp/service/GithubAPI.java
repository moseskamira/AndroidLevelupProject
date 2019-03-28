package com.example.androidcodelabapp.service;

import com.example.androidcodelabapp.model.GithubUsers;
import com.example.androidcodelabapp.model.GithubUsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {
    @GET("search/users?q=type:User+location:Nairobi+language:JAVA")
    Call<GithubUsersResponse> getAllDevelopers();

    @GET("users/{githubUserName}")
    Call<GithubUsers> getDeveloperProfile(@Path("githubUserName") String githubUserName);

}
