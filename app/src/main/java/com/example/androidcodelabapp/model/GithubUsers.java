package com.example.androidcodelabapp.model;

import com.google.gson.annotations.SerializedName;

public class GithubUsers {

    @SerializedName("avatar_url")
    String profileImage;

    @SerializedName("login")
    String userName;

    @SerializedName("html_url")
    String profile;

    @SerializedName("company")
    String organization;


    public String getProfileImage() {
        return profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public String getOrganization() {
        return organization;
    }

    public String getProfile() {
        return profile;
    }

}
