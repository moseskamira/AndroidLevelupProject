package com.example.androidcodelabapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidcodelabapp.R;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getIncomingIntent();
    }
    private void getIncomingIntent(){
        if(getIntent().hasExtra("gitUserName") && getIntent().hasExtra("profileImage")){
            String userName = getIntent().getStringExtra("gitUserName");
            String profileImage = getIntent().getStringExtra("profileImage");
            setProfile(userName, profileImage);
        }
    }
    private void setProfile(String userName, String profileImage){
        TextView name = findViewById(R.id.gitusername);
        name.setText(userName);
        ImageView image = findViewById(R.id.image);
        Glide.with(this).asBitmap().load(profileImage).into(image);

    }
}
