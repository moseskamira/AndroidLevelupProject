package com.example.androidcodelabapp.view;

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

import java.util.List;

public class MainActivity extends AppCompatActivity implements AllDevelopersView {
    private RecyclerView recyclerView;
    private GithubPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        presenter.getDevelopers(this);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        presenter = new GithubPresenter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }
    public void showDeveloperDetails(GithubUsers profileInfo){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("gitUserName", profileInfo.getUserName());
        intent.putExtra("profileImage",profileInfo.getProfileImage());
        startActivity(intent);

    }

    @Override
    public void showDevelopers(GithubUsersResponse response) {
        List<GithubUsers> allDevelopers = response.getGithubUsers();
        recyclerView.setAdapter(new GithubUsersAdapter(this, allDevelopers));
    }


}
