package com.hamidrezabashiri.signaling.data.model

import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("bio")
    var bio: String? = null,
    @SerializedName("phone")
    var phone: String? = null,
    @SerializedName("avatar")
    var avatar: String? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("updated_at")
    var updatedAt: String? = null,
    @SerializedName("is_online")
    var isOnline: Int? = null,
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("last_seen")
    var lastSeen: Int? = null
)

