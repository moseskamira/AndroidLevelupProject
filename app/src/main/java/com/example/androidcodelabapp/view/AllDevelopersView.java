package com.example.androidcodelabapp.view;

import com.example.androidcodelabapp.model.GithubUsersResponse;

public interface AllDevelopersView {
    public void showDevelopers(GithubUsersResponse response);
    public void showError();
}
