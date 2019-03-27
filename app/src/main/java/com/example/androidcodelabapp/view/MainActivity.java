package com.example.androidcodelabapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.androidcodelabapp.R;
import com.example.androidcodelabapp.adapter.GithubUsersAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> userNames = new ArrayList<>();
    private ArrayList<String> profileImage = new ArrayList<>();
    private ArrayList<String> getGithubUrl = new ArrayList<>();
    private ArrayList<String> getOrganization = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBindDeveloper();

    }

    private void initBindDeveloper() {
        profileImage.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        userNames.add("moseskamira");
        profileImage.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png");
        userNames.add("evamaina");
        profileImage.add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
        userNames.add("emmanuelomona");
        profileImage.add("https://homepages.cae.wisc.edu/~ece533/images/girl.png");
        userNames.add("janetwalters");
        profileImage.add("https://homepages.cae.wisc.edu/~ece533/images/watch.png");
        userNames.add("kellykeith");
        profileImage.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.png");
        userNames.add("johnsonagile");

        initGithubUsersAdapter();

    }

    private void initGithubUsersAdapter() {
        recyclerView = findViewById(R.id.recyclerview);
        GithubUsersAdapter adapter = new GithubUsersAdapter(this, userNames, profileImage, getGithubUrl, getOrganization);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

    }


}
