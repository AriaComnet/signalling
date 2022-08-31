package com.hamidrezabashiri.signaling.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Login(
    @SerializedName("code")
    @Expose
    var code: String? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("data")
    @Expose
    var data: DataLogin? = DataLogin()
)