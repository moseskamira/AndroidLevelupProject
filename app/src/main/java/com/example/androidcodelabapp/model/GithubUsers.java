package com.example.androidcodelabapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GithubUsers implements Parcelable {
    public static final Creator<GithubUsers> CREATOR = new Creator<GithubUsers>() {
        @Override
        public GithubUsers createFromParcel(Parcel source) {
            return new GithubUsers(source);
        }

        @Override
        public GithubUsers[] newArray(int size) {
            return new GithubUsers[size];
        }
    };

    @SerializedName("avatar_url")
    String profileImage;

    @SerializedName("login")
    String userName;

    @SerializedName("html_url")
    String profile;

    @SerializedName("company")
    String organization;

    protected GithubUsers(Parcel source) {
        profileImage = source.readString();
        userName = source.readString();
        organization = source.readString();
        profile = source.readString();
    }


    public String getProfileImage() {
        return profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public String getOrganization() {
        return organization;
    }

    public String getProfile() {
        return profile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(profileImage);
        dest.writeString(userName);
        dest.writeString(organization);
        dest.writeString(profile);
    }

}
