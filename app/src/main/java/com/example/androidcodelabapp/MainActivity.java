package com.example.androidcodelabapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList <String> userNames = new ArrayList<>();
    private ArrayList <String> imageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageBitmaps();

//        launchDetailActivity();

    }
    private void initImageBitmaps(){
        imageUrls.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        userNames.add("moseskamira");
        imageUrls.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png");
        userNames.add("evamaina");
        imageUrls.add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
        userNames.add("emmanuelomona");
        imageUrls.add("https://homepages.cae.wisc.edu/~ece533/images/girl.png");
        userNames.add("janetwalters");
        imageUrls.add("https://homepages.cae.wisc.edu/~ece533/images/watch.png");
        userNames.add("kellykeith");
        imageUrls.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.png");
        userNames.add("johnsonagile");

        initRecyclerViewAdapter();

    }
    private void initRecyclerViewAdapter(){
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, userNames, imageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

    }


}
