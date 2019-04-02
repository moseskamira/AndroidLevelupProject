package com.example.androidcodelabapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidcodelabapp.R;
import com.example.androidcodelabapp.model.GithubUsers;
import com.example.androidcodelabapp.presenter.GithubPresenter;

public class DetailActivity extends AppCompatActivity implements SingleDeveloperView {
    private String githubUserName;
    Button share;
    GithubUsers sharedInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getIncomingIntent();
        GithubPresenter presenter = new GithubPresenter();
        presenter.getDeveloperProfile(githubUserName, this
        );
        share = findViewById(R.id.sharebutton);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, String.format("Checkout this awesome developer @%s, %s.", sharedInfo.getUserName(), sharedInfo.getProfile()));
                startActivity(Intent.createChooser(shareIntent, String.format("Checkout this awesome developer @%s, %s.:", sharedInfo.getUserName(), sharedInfo.getProfile())));
            }

        });
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("gitUserName") && getIntent().hasExtra("profileImage")) {
            githubUserName = getIntent().getStringExtra("gitUserName");
            String profileImage = getIntent().getStringExtra("profileImage");
            setProfile(githubUserName, profileImage);
        }
    }

    private void setProfile(String userName, String profileImage) {
        TextView name = findViewById(R.id.gitusername);
        name.setText(userName);
        ImageView image = findViewById(R.id.image);
        Glide.with(this).asBitmap().load(profileImage).into(image);


    }

    @Override
    public void showDeveloperProfile(GithubUsers profile) {
        sharedInfo = profile;
        View view = findViewById(R.id.detail_activity);
        TextView githubUrl = view.findViewById(R.id.githuburl);
        TextView organization = view.findViewById(R.id.org);

        githubUrl.setText(profile.getProfile());
        organization.setText(profile.getOrganization());


    }


}
