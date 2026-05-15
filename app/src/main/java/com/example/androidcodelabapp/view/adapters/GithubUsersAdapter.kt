package com.example.androidcodelabapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcodelabapp.databinding.LayoutListitemBinding
import com.example.androidcodelabapp.model.domain.entities.GithubUser
import com.example.androidcodelabapp.view.activities.UsersActivity

class GithubUsersAdapter(
    private val mContext: Context,
    private val allDevelopers: List<GithubUser>
) : RecyclerView.Adapter<GithubUsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutListitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = allDevelopers[position]

        // Set username
        holder.binding.gitusername.text = user.userName

        // Load profile image
        Glide.with(mContext)
            .load(user.profileImage)
            .into(holder.binding.image1)

        // Handle click
        holder.binding.parentLayout.setOnClickListener {
            (mContext as UsersActivity).loadDetailActivity(user)
        }
    }

    override fun getItemCount(): Int = allDevelopers.size

    inner class ViewHolder(val binding: LayoutListitemBinding) : RecyclerView.ViewHolder(binding.root)
}