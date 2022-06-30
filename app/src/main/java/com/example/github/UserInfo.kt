package com.example.github

import com.google.gson.annotations.SerializedName

data class UserInfo
    (
        @SerializedName("login") var login: String,
        @SerializedName("avatar_url") var avatarUrl: String,
        @SerializedName("location") var location: String
            ) {
}