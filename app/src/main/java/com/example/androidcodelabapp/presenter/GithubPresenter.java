package com.example.androidcodelabapp.presenter;

import android.util.Log;

import com.example.androidcodelabapp.model.GithubUsers;
import com.example.androidcodelabapp.model.GithubUsersResponse;
import com.example.androidcodelabapp.service.GithubService;

import com.example.androidcodelabapp.service.GithubAPI;
import com.example.androidcodelabapp.view.AllDevelopersView;
import com.example.androidcodelabapp.view.SingleDeveloperView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubPresenter {
    static final String TAG = "Something Went Wrong!";
    GithubAPI apiInterface;

    public GithubPresenter() {
        apiInterface = new GithubService().getRetrofit();
    }

    public void getDevelopers(final AllDevelopersView allDevelopersView) {
        apiInterface.getAllDevelopers().enqueue(new Callback<GithubUsersResponse>() {
            @Override
            public void onResponse(Call<GithubUsersResponse> call, Response<GithubUsersResponse> response) {
                allDevelopersView.showDevelopers(response.body());

            }

            @Override
            public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                Log.e(TAG, "Something Went Wrong");

            }
        });
    }

    public void getDeveloperProfile(String handle, final SingleDeveloperView singleDeveloperView) {
        apiInterface.getDeveloperProfile(handle).enqueue(new Callback<GithubUsers>() {
            @Override
            public void onResponse(Call<GithubUsers> call, Response<GithubUsers> response) {
                singleDeveloperView.showDeveloperProfile(response.body());


            }

            @Override
            public void onFailure(Call<GithubUsers> call, Throwable t) {
                Log.e(TAG, "Something Went Wrong");


            }
        });
    }


}
