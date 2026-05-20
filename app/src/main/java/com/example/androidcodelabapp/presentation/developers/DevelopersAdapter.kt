package com.example.androidcodelabapp.presentation.developers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcodelabapp.databinding.LayoutListitemBinding
import com.example.androidcodelabapp.data.network.dto.GithubUser

class DevelopersAdapter(
    private val mContext: Context,
    private val allDevelopers: List<GithubUser>
) : RecyclerView.Adapter<DevelopersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutListitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = allDevelopers[position]
        holder.binding.gitusername.text = user.userName
        Glide.with(mContext)
            .load(user.profileImage)
            .into(holder.binding.image1)
        holder.binding.parentLayout.setOnClickListener {
            (mContext as DevelopersActivity).loadDetailActivity(user)
        }
    }

    override fun getItemCount(): Int = allDevelopers.size
    inner class ViewHolder(val binding: LayoutListitemBinding) :
        RecyclerView.ViewHolder(binding.root)
}