package com.example.androidcodelabapp.view


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.androidcodelabapp.R
import com.example.androidcodelabapp.adapter.GithubUsersAdapter
import com.example.androidcodelabapp.model.GithubUser
import com.example.androidcodelabapp.model.GithubUsersResponse
import com.example.androidcodelabapp.presenter.GithubPresenter
import com.example.androidcodelabapp.util.CheckNetworkConnection
import com.google.android.material.snackbar.Snackbar

class UsersActivity : AppCompatActivity(), AllDevelopersContract, SwipeRefreshLayout.OnRefreshListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: GithubPresenter
    private lateinit var devSwipe: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var allDevelopers: ArrayList<GithubUser>
    private var listState: Parcelable? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager

    companion object {
        const val LIST_STATE_KEY = "recycler_list_state"
        const val GITHUB_USERS = "retrieved_github_users"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
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

    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        state.putParcelableArrayList(GITHUB_USERS, allDevelopers)
        listState = layoutManager.onSaveInstanceState()
        state.putParcelable(LIST_STATE_KEY, listState)
    }
    override fun onRestoreInstanceState(state: Bundle) {
        super.onRestoreInstanceState(state)
        super.onRestoreInstanceState(state)
        allDevelopers = state.getParcelableArrayList(GITHUB_USERS)!!
        listState = state.getParcelable(LIST_STATE_KEY)

    }



    override fun onResume() {
        super.onResume()
        if (listState != null) {
            recyclerView.adapter = GithubUsersAdapter(this, allDevelopers)
            layoutManager.onRestoreInstanceState(listState)
            progressBar.visibility = View.GONE
        }
    }

    fun showDeveloperDetails(profileInfo: GithubUser) {
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
