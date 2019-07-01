package com.example.androidcodelabapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.bumptech.glide.Glide
import com.example.androidcodelabapp.model.GithubUsers
import com.example.androidcodelabapp.view.MainActivity

import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_detail.view.gitusername
import kotlinx.android.synthetic.main.layout_listitem.view.*

class GithubUsersAdapter(private val mContext: Context, private val allDevelopers: List<GithubUsers>)
    : RecyclerView.Adapter<GithubUsersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.androidcodelabapp
                .R.layout.layout_listitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val gitUserName = holder.itemView.gitusername
        gitUserName.text = allDevelopers[position].userName
        Glide.with(mContext).asBitmap().load(allDevelopers[position].profileImage)
                .into(holder.profileImage)
        holder.parentLayout.setOnClickListener {
            val mainActivity = mContext as MainActivity
            mainActivity.showDeveloperDetails(allDevelopers[position])
        }
    }

    override fun getItemCount(): Int {
        return allDevelopers.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileImage: CircleImageView = itemView.image1
        var parentLayout: LinearLayout = itemView.parent_layout

    }
}
