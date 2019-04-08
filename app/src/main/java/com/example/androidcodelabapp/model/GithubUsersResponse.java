package com.example.androidcodelabapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GithubUsersResponse {
    @SerializedName("items")
    ArrayList<GithubUsers> githubUsers;

    public GithubUsersResponse(){
        githubUsers = new ArrayList<>();

    }
    public ArrayList<GithubUsers> getGithubUsers(){
        return githubUsers;
    }
}
