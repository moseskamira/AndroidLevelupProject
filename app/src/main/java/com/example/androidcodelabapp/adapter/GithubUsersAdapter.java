package com.example.androidcodelabapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidcodelabapp.view.DetailActivity;
import com.example.androidcodelabapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GithubUsersAdapter extends RecyclerView.Adapter<GithubUsersAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> userName;
    private ArrayList<String> profileImage;
    private ArrayList<String> githubUrl;
    private ArrayList<String> organization;
    private Context mContext;

    public GithubUsersAdapter(Context context, ArrayList<String> userName, ArrayList<String> profileImage, ArrayList<String> githubUrl, ArrayList<String> organization) {
        this.userName = userName;
        this.profileImage = profileImage;
        this.githubUrl = githubUrl;
        this.organization = organization;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int location = position;
        Glide.with(mContext).asBitmap().load(profileImage.get(position)).into(holder.profileImage);
        holder.gitUserName.setText(userName.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("gitUserName", userName.get(location));
                intent.putExtra("profileImage", profileImage.get(location));
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return profileImage.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView gitUserName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gitUserName = itemView.findViewById(R.id.gitusername);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            profileImage = itemView.findViewById(R.id.image);

        }
    }
}
