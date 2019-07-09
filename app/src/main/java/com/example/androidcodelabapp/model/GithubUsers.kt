package com.example.androidcodelabapp.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class GithubUsers : Parcelable {
    @SerializedName("avatar_url")
    var profileImage: String? = null

    @SerializedName("login")
    var userName: String? = null

    @SerializedName("html_url")
    var profile: String? = null

    @SerializedName("company")
    var organization: String? = null

    constructor(image: String, username: String, profile: String, organization: String) {
        this.profileImage = image
        this.userName = username
        this.profile = profile
        this.organization = organization
    }

    companion object CREATOR : Parcelable.Creator<GithubUsers> {
        override fun createFromParcel(source: Parcel): GithubUsers = GithubUsers(source)
        override fun newArray(size: Int): Array<GithubUsers?> = arrayOfNulls(size)
    }

    constructor(source: Parcel) {
        profileImage = source.readString()
        userName = source.readString()
        organization = source.readString()
        profile = source.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(profileImage)
        dest.writeString(userName)
        dest.writeString(organization)
        dest.writeString(profile)
    }
}
