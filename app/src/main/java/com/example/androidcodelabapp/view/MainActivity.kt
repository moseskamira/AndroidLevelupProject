package com.example.androidcodelabapp.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.VisibleForTesting
import android.support.design.widget.Snackbar
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar


import com.example.androidcodelabapp.R
import com.example.androidcodelabapp.adapter.GithubUsersAdapter
import com.example.androidcodelabapp.model.GithubUsers
import com.example.androidcodelabapp.model.GithubUsersResponse
import com.example.androidcodelabapp.presenter.GithubPresenter
import com.example.androidcodelabapp.util.CheckNetworkConnection

import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), AllDevelopersView, SwipeRefreshLayout.OnRefreshListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: GithubPresenter
    private lateinit var devSwipe: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var allDevelopers: ArrayList<GithubUsers>
    private var listState: Parcelable? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager
    @get:VisibleForTesting
    var countingIdlingResource = CountingIdlingResource("Main")
        internal set

    companion object {
        const val LIST_STATE_KEY = "recycler_list_state"
        const val GITHUB_USERS = "retrieved_github_users"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = GithubPresenter()
        recyclerView = findViewById(R.id.recyclerview)
        devSwipe = findViewById(R.id.swipe)
        progressBar = findViewById(R.id.progbar)
        recyclerView.setHasFixedSize(true)
        layoutManager = if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
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
            countingIdlingResource.increment()
            presenter.getDevelopers(this)
        } else {
            progressBar.visibility = View.GONE
            val snackbar = Snackbar.make(findViewById(R.id.cordinator),
                    "No Internet Connection, Make Sure You Hava Mobile Data or Wifi",
                    Snackbar.LENGTH_INDEFINITE)
            snackbar.show()
        }
    }

    override fun showDevelopers(response: GithubUsersResponse) {

        allDevelopers = response.getGithubUsers()
        recyclerView.adapter = GithubUsersAdapter(this, allDevelopers)
        devSwipe.isRefreshing = false
        progressBar.visibility = View.GONE
        countingIdlingResource.decrement()
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        state.putParcelableArrayList(GITHUB_USERS, allDevelopers)
        listState = layoutManager.onSaveInstanceState()
        state.putParcelable(LIST_STATE_KEY, listState)
    }

    override fun onRestoreInstanceState(state: Bundle?) {
        super.onRestoreInstanceState(state)
        if (state != null) {
            allDevelopers = state.getParcelableArrayList(GITHUB_USERS)!!
            listState = state.getParcelable(LIST_STATE_KEY)
        }
    }

    override fun onResume() {
        super.onResume()
        if (listState != null) {
            recyclerView.adapter = GithubUsersAdapter(this, allDevelopers)
            layoutManager.onRestoreInstanceState(listState)
            progressBar.visibility = View.GONE
        }
    }

    fun showDeveloperDetails(profileInfo: GithubUsers) {
        val intent = Intent(this, DetailActivity::class.java)
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
