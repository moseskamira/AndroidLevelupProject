package com.example.androidcodelabapp.view;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;

import com.example.androidcodelabapp.R;
import com.example.androidcodelabapp.adapter.GithubUsersAdapter;
import com.example.androidcodelabapp.model.GithubUsers;
import com.example.androidcodelabapp.model.GithubUsersResponse;
import com.example.androidcodelabapp.presenter.GithubPresenter;
import com.example.androidcodelabapp.util.CheckNetworkConnection;


import java.util.List;

public class MainActivity extends AppCompatActivity implements AllDevelopersView {
    private RecyclerView recyclerView;
    private GithubPresenter presenter;
    private CoordinatorLayout coordinatorLayout;
    private CheckNetworkConnection networkConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        presenter.getDevelopers(this);
        coordinatorLayout = findViewById(R.id.cordinator);
        networkConnection = new CheckNetworkConnection(this);
        if (!networkConnection.isConnected()) {
            showSnackbar();
        } else {

        }
    }

    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Internet Connection, Make Sure You Hava Mobile Data or Wifi", Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        presenter = new GithubPresenter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    public void showDeveloperDetails(GithubUsers profileInfo) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("gitUserName", profileInfo.getUserName());
        intent.putExtra("profileImage", profileInfo.getProfileImage());
        startActivity(intent);

    }

    @Override
    public void showDevelopers(GithubUsersResponse response) {
        List<GithubUsers> allDevelopers = response.getGithubUsers();
        recyclerView.setAdapter(new GithubUsersAdapter(this, allDevelopers));
    }


}
