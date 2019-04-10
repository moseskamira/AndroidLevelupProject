package com.example.androidcodelabapp.view;

import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.idling.CountingIdlingResource;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AllDevelopersView, SwipeRefreshLayout.OnRefreshListener{
    public static final String LIST_STATE_KEY = "recycler_list_state";
    public static final String GITHUB_USERS = "retrieved_github_users";
    private RecyclerView recyclerView;
    private GithubPresenter presenter;
    private SwipeRefreshLayout devSwipe;
    private ProgressBar progressBar;
    ArrayList<GithubUsers> allDevelopers;
    private Parcelable listState = null;
    RecyclerView.LayoutManager layoutManager;
    CountingIdlingResource countingIdlingResource = new CountingIdlingResource("Main");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        devSwipe = findViewById(R.id.swipe);
        progressBar = findViewById(R.id.progbar);
        recyclerView.setHasFixedSize(true);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(this, 3);
        } else {
            layoutManager = new GridLayoutManager(this, 4);
        }
        recyclerView.setLayoutManager(layoutManager);
        presenter = new GithubPresenter();
        loadGithubUsers();

        devSwipe.setOnRefreshListener(this);

        devSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadGithubUsers();
            }
        });

    }

    public void loadGithubUsers() {
        if (new CheckNetworkConnection(this).isConnected()) {
            countingIdlingResource.increment();
            presenter.getDevelopers(this);

        } else {
            progressBar.setVisibility(View.GONE);
            Snackbar snackbar = Snackbar.make(findViewById(R.id.cordinator), "No Internet Connection, Make Sure You Hava Mobile Data or Wifi", Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        }
    }
    @Override
    public void showDevelopers(GithubUsersResponse response) {

        allDevelopers = response.getGithubUsers();
        recyclerView.setAdapter(new GithubUsersAdapter(this, allDevelopers));
        devSwipe.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
        countingIdlingResource.decrement();
    }

    protected void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        state.putParcelableArrayList(GITHUB_USERS, allDevelopers);
        listState = layoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, listState);

    }
    protected void onRestoreInstanceState(Bundle state){
        super.onRestoreInstanceState(state);
        if(state != null){
            allDevelopers = state.getParcelableArrayList(GITHUB_USERS);
            listState = state.getParcelable(LIST_STATE_KEY);

        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(listState != null){
            recyclerView.setAdapter(new GithubUsersAdapter(this, allDevelopers));
            layoutManager.onRestoreInstanceState(listState);
            progressBar.setVisibility(View.GONE);


        }
    }


    public void showDeveloperDetails(GithubUsers profileInfo) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("gitUserName", profileInfo.getUserName());
        intent.putExtra("profileImage", profileInfo.getProfileImage());
        startActivity(intent);

    }
    @Override
    public void onRefresh(){
        refreshDevs();
    }
    public void refreshDevs(){
        loadGithubUsers();
        devSwipe.setRefreshing(false);
    }

    @VisibleForTesting
    public CountingIdlingResource getCountingIdlingResource(){
        return countingIdlingResource;
    }


}
