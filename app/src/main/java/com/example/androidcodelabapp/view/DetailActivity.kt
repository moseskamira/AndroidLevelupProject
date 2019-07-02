package com.example.androidcodelabapp.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

import com.bumptech.glide.Glide
import com.example.androidcodelabapp.model.GithubUsers
import com.example.androidcodelabapp.presenter.GithubPresenter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), SingleDeveloperView {
    private lateinit var githubUserName: String
    private lateinit var share: Button
    private lateinit var sharedInfo: GithubUsers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.androidcodelabapp.R.layout.activity_detail)
        getIncomingIntent()
        val presenter = GithubPresenter()
        presenter.getDeveloperProfile(githubUserName, this
        )
        share = findViewById(com.example.androidcodelabapp.R.id.sharebutton)
        share.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, String
                    .format("Checkout this awesome developer @%s, %s.",
                            sharedInfo.userName, sharedInfo.profile))
            startActivity(Intent.createChooser(shareIntent, String
                    .format("Checkout this awesome developer @%s, %s.:",
                            sharedInfo.userName, sharedInfo.profile)))
        }
    }

    private fun getIncomingIntent() {
        if (intent.hasExtra("gitUserName") && intent.hasExtra("profileImage")) {
            githubUserName = intent.getStringExtra("gitUserName")
            val profileImage = intent.getStringExtra("profileImage")
            setProfile(githubUserName, profileImage)
        }
    }

    private fun setProfile(userName: String?, profileImage: String) {
        val name = gitusername
        name.text = userName
        val image = image
        Glide.with(this).asBitmap().load(profileImage).into(image)
    }

    override fun showDeveloperProfile(profile: GithubUsers) {
        sharedInfo = profile
        val githubUrl = githuburl
        val organization = org
        if (profile != null) {
            githubUrl.text = profile.profile
            organization.text = profile.organization
        }
    }
}
