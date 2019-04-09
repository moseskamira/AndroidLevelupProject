package com.example.androidcodelabapp.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class GithubUsersTest {

    @Test
    public void createGitHubUser(){
        GithubUsers newGithubUser = new GithubUsers("image", "username", "profile", "organzation");
        final String username = "TheDancerCodes";
        final String image = "https://avatars2.githubusercontent.com/u/6739804?v=4";
        final String profile = "https://github.com/TheDancerDodes";
        final String organization = "Andela";

        newGithubUser.userName = username;
        newGithubUser.profileImage = image;
        newGithubUser.profile = profile;
        newGithubUser.organization = organization;



        assertEquals(username, newGithubUser.getUserName());
        assertEquals(image, newGithubUser.getProfileImage());
        assertEquals(profile, newGithubUser.getProfile());
        assertEquals(organization, newGithubUser.getOrganization());


    }

}