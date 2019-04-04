package com.example.androidcodelabapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidcodelabapp.model.GithubUsers;
import com.example.androidcodelabapp.R;
import com.example.androidcodelabapp.view.MainActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GithubUsersAdapter extends RecyclerView.Adapter<GithubUsersAdapter.ViewHolder> {
    public final Context mContext;
    public final List<GithubUsers> allDevelopers;

    public GithubUsersAdapter(Context context, List<GithubUsers> allDevelopers) {
        this.mContext = context;
        this.allDevelopers = allDevelopers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView gitUserName = holder.itemView.findViewById(R.id.gitusername);
        gitUserName.setText(allDevelopers.get(position).getUserName());
        Glide.with(mContext).asBitmap().load(allDevelopers.get(position).getProfileImage()).into(holder.profileImage);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) mContext;
                mainActivity.showDeveloperDetails(allDevelopers.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return allDevelopers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView gitUserName;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gitUserName = itemView.findViewById(R.id.gitusername);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            profileImage = itemView.findViewById(R.id.image);

        }
    }
}
