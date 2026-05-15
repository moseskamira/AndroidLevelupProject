package com.example.androidcodelabapp.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import com.example.androidcodelabapp.databinding.ActivityDetailBinding
import com.example.androidcodelabapp.model.data.repositories.DeveloperRepository
import com.example.androidcodelabapp.model.domain.entities.GithubUser
import com.example.androidcodelabapp.presenter.DeveloperProfilePresenter
import com.example.androidcodelabapp.view.contracts.SingleDeveloperContract

class DetailActivity : AppCompatActivity(), SingleDeveloperContract {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var sharedInfo: GithubUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        // ViewBinding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TOOLBAR SETUP
//        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Developer Profile"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val presenter = DeveloperProfilePresenter(DeveloperRepository())

        val githubUserName = intent.getStringExtra("gitUserName")
        val profileImage = intent.getStringExtra("profileImage")

        if (!githubUserName.isNullOrEmpty() && !profileImage.isNullOrEmpty()) {
            setProfile(githubUserName, profileImage)
            presenter.getDeveloperProfile(githubUserName, this)
        }

        binding.sharebutton.setOnClickListener {

            if (::sharedInfo.isInitialized) {

                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"

                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Checkout this awesome developer @${sharedInfo.userName}, ${sharedInfo.profile}"
                    )
                }

                startActivity(
                    Intent.createChooser(
                        shareIntent,
                        "Share Developer Info"
                    )
                )
            }
        }
    }

    private fun setProfile(userName: String, profileImage: String) {
        binding.gitusername.text = userName

        Glide.with(this)
            .load(profileImage)
            .into(binding.image)
    }

    override fun showDeveloperProfile(profile: GithubUser) {
        sharedInfo = profile

        binding.githuburl.text = profile.profile
        binding.org.text = profile.organization
    }

    override fun showError(s: String) {
        binding.org.text = s
    }
}