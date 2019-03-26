package com.example.androidcodelabapp;

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

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mgitUserName;
    private ArrayList<String> mprofileImage;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> gitUserName, ArrayList<String> profileImage) {
        this.mgitUserName = gitUserName;
        this.mprofileImage = profileImage;
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
        Glide.with(mContext).asBitmap().load(mprofileImage.get(position)).into(holder.profileImage);
        holder.gitUserName.setText(mgitUserName.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("gitUserName", mgitUserName.get(location));
                intent.putExtra("profileImage", mprofileImage.get(location));
                mContext.startActivity(intent);
            }
        });
    }
//    public void setOnclickListener(){
//
//
//    }

    @Override
    public int getItemCount() {
        return mprofileImage.size();
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
