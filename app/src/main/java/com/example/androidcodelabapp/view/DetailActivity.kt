package com.example.androidcodelabapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.androidcodelabapp.databinding.ActivityDetailBinding
import com.example.androidcodelabapp.model.GithubUsers
import com.example.androidcodelabapp.presenter.GithubPresenter

class DetailActivity : AppCompatActivity(), SingleDeveloperView {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var sharedInfo: GithubUsers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = GithubPresenter()

        val githubUserName = intent.getStringExtra("gitUserName")
        val profileImage = intent.getStringExtra("profileImage")

        if (!githubUserName.isNullOrEmpty() && !profileImage.isNullOrEmpty()) {
            setProfile(githubUserName, profileImage)
            presenter.getDeveloperProfile(githubUserName, this)
        }

        binding.sharebutton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Checkout this awesome developer @${sharedInfo.userName}, ${sharedInfo.profile}."
                )
            }
            startActivity(Intent.createChooser(shareIntent, "Share Developer Info"))
        }
    }

    private fun setProfile(userName: String, profileImage: String) {
        binding.gitusername.text = userName
        Glide.with(this).asBitmap().load(profileImage).into(binding.image)
    }

    override fun showDeveloperProfile(profile: GithubUsers) {
        sharedInfo = profile
        binding.githuburl.text = profile.profile
        binding.org.text = profile.organization
    }
}