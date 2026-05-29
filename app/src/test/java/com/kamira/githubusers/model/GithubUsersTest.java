package com.kamira.githubusers.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.kamira.githubusers.data.network.dto.GithubUserDto;


public class GithubUsersTest {

    @Test
    public void createGitHubUser(){
        GithubUserDto newGithubUser = new GithubUserDto("image", "username", "profile", "organzation");
        final String username = "TheDancerCodes";
        final String image = "https://avatars2.githubusercontent.com/u/6739804?v=4";
        final String profile = "https://github.com/TheDancerDodes";
        final String organization = "Andela";

        newGithubUser.setUserName(username);
        newGithubUser.setProfileImage(image);
        newGithubUser.setProfile(profile);
        newGithubUser.setOrganization(organization);

        assertEquals(username, newGithubUser.getUserName());
        assertEquals(image, newGithubUser.getProfileImage());
        assertEquals(profile, newGithubUser.getProfile());
        assertEquals(organization, newGithubUser.getOrganization());
    }
}