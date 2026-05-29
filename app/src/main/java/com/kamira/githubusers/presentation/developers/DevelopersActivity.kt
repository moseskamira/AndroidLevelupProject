package com.kamira.githubusers.presentation.developers


import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kamira.githubusers.R
import com.kamira.githubusers.data.repositories.DeveloperRepositoryImpl
import com.kamira.githubusers.databinding.ActivityUsersBinding
import com.kamira.githubusers.domain.models.GitHubUser
import com.kamira.githubusers.domain.models.GitHubUserResponse
import com.kamira.githubusers.presentation.developer_profile.DeveloperProfileActivity
import com.kamira.githubusers.util.CheckNetworkConnection
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar

class DevelopersActivity : AppCompatActivity(), DevelopersContract,
    SwipeRefreshLayout.OnRefreshListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: DevelopersPresenter
    private lateinit var devSwipe: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var allDevelopers: ArrayList<GitHubUser>
    private var listState: Parcelable? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private  lateinit var binding : ActivityUsersBinding

    companion object {
        const val LIST_STATE_KEY = "recycler_list_state"
        const val GITHUB_USERS = "retrieved_github_users"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.title = "Java Developers"
        presenter = DevelopersPresenter(DeveloperRepositoryImpl())
        recyclerView = findViewById(R.id.recyclerview)
        devSwipe = findViewById(R.id.swipe)
        progressBar = findViewById(R.id.progbar)
        recyclerView.setHasFixedSize(true)
        layoutManager =
            if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(this, 3)
            } else {
                GridLayoutManager(this, 4)
            }
        recyclerView.layoutManager = layoutManager
        loadGithubUsers()
        devSwipe.setOnRefreshListener(this)
        devSwipe.setOnRefreshListener { loadGithubUsers() }
    }

    private fun loadGithubUsers() {
        if (CheckNetworkConnection(this).isConnected) {
            presenter.getDevelopers(this)

        } else {
            progressBar.visibility = View.GONE
            val snackbar = Snackbar.make(
                findViewById(R.id.cordinator),
                "No Internet Connection, Make Sure You Hava Mobile Data or Wifi",
                Snackbar.LENGTH_INDEFINITE
            )
            snackbar.show()
        }
    }

    override fun showDevelopers(response: GitHubUserResponse) {
        allDevelopers = response.githubUsers
        recyclerView.adapter = DevelopersAdapter(this, allDevelopers)
        devSwipe.isRefreshing = false
        progressBar.visibility = View.GONE

    }

    override fun showError(s: String) {
        progressBar.visibility = View.GONE
        Snackbar.make(
            findViewById(android.R.id.content),
            s,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(GITHUB_USERS, allDevelopers)
        listState = layoutManager.onSaveInstanceState()
        outState.putParcelable(LIST_STATE_KEY, listState)
    }

    override fun onRestoreInstanceState(state: Bundle) {
        super.onRestoreInstanceState(state)
        allDevelopers = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            state.getParcelableArrayList(GITHUB_USERS, GitHubUser::class.java)
        } else {
            @Suppress("DEPRECATION")
            state.getParcelableArrayList(GITHUB_USERS)
        } ?: arrayListOf()

        listState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            state.getParcelable(LIST_STATE_KEY, Parcelable::class.java)
        } else {
            @Suppress("DEPRECATION")
            state.getParcelable(LIST_STATE_KEY)
        }
    }


    override fun onResume() {
        super.onResume()
        if (listState != null) {
            recyclerView.adapter = DevelopersAdapter(this, allDevelopers)
            layoutManager.onRestoreInstanceState(listState)
            progressBar.visibility = View.GONE
        }
    }

    fun loadDetailActivity(profileInfo: GitHubUser) {
        val intent = Intent(this, DeveloperProfileActivity::class.java)
        intent.putExtra("gitUserName", profileInfo.userName)
        intent.putExtra("profileImage", profileInfo.profileImage)
        startActivity(intent)
    }

    override fun onRefresh() {
        refreshDevs()
    }

    private fun refreshDevs() {
        loadGithubUsers()
        devSwipe.isRefreshing = false
    }
}
