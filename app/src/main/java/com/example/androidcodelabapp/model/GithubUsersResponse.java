package com.example.androidcodelabapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GithubUsersResponse {
    @SerializedName("items")
    List<GithubUsers> githubUsers;

    public GithubUsersResponse(){
        githubUsers = new ArrayList<GithubUsers>();

    }
    public List<GithubUsers> getGithubUsers(){
        return githubUsers;
    }
}
