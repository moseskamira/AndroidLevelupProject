package com.example.androidcodelabapp.view;

import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;


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
    private SwipeRefreshLayout devSwipe;
    private ProgressBar progressBar;
    public static final String LIST_STATE_KEY = "recycler_state";
    List<GithubUsers> allDevelopers;
    Parcelable listState;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runProgressBar();
        initRecyclerView();
        loadGithubUsers();
        devSwipe = findViewById(R.id.swipe);
        devSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadGithubUsers();
            }
        });

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
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

        allDevelopers = response.getGithubUsers();
        recyclerView.setAdapter(new GithubUsersAdapter(this, allDevelopers));
        devSwipe.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }

    void loadGithubUsers() {
        if (new CheckNetworkConnection(this).isConnected()) {

            presenter = new GithubPresenter();
            presenter.getDevelopers(this);
        } else {
            progressBar.setVisibility(View.GONE);
            Snackbar snackbar = Snackbar.make(findViewById(R.id.cordinator), "No Internet Connection, Make Sure You Hava Mobile Data or Wifi", Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        }
    }

    public void runProgressBar() {
        progressBar = (ProgressBar) findViewById(R.id.progbar);
        Thread thread = new Thread() {
            @Override
            public void run() {
                loadGithubUsers();
            }
        };
        thread.start();
    }
}
