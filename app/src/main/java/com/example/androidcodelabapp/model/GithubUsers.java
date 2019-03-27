package com.example.androidcodelabapp.model;
import com.google.gson.annotations.SerializedName;

public class GithubUsers {

    @SerializedName("avatar_url")
    String profileImage;

    @SerializedName("login")
    String userName;

    @SerializedName("epos_url")
    String githubUrl;
    @SerializedName("html_url")
    String profile;

    @SerializedName("rganizations_url")
    String organization;


    public String getProfileImage() {
        return profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getOrganization() {
        return organization;
    }

    public String getProfile() {
        return profile;
    }

}
