package com.hamidrezabashiri.signaling.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Login(
    @SerializedName("code")
    var code: String? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("data")
    var data: DataLogin? = DataLogin()
)