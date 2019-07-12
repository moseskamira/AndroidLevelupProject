package com.example.androidcodelabapp.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class GithubUsers( profileImage: String?, userName: String?, profile: String?,
                   organization: String?) : Parcelable {
    @SerializedName("avatar_url")
    var profileImage: String? = null

    @SerializedName("login")
    var userName: String? = null

    @SerializedName("html_url")
    var profile: String? = null

    @SerializedName("company")
    var organization: String? = null

    init {
        this.profileImage = profileImage
        this.userName = userName
        this.profile = profile
        this.organization = organization
    }

    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString(),
            parcel.readString(), parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(profileImage)
        parcel.writeString(userName)
        parcel.writeString(profile)
        parcel.writeString(organization)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GithubUsers> {
        override fun createFromParcel(parcel: Parcel): GithubUsers {
            return GithubUsers(parcel)
        }

        override fun newArray(size: Int): Array<GithubUsers?> {
            return arrayOfNulls(size)
        }
    }
}
