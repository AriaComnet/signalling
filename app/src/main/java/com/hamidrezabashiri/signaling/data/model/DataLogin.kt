package com.hamidrezabashiri.signaling.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("bio")
    @Expose
    var bio: String? = null,
    @SerializedName("phone")
    @Expose
    var phone: String? = null,
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null,
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,
    @SerializedName("is_online")
    @Expose
    var isOnline: Int? = null,
    @SerializedName("token")
    @Expose
    var token: String? = null,
    @SerializedName("last_seen")
    @Expose
    var lastSeen: Int? = null
)

