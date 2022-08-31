package com.hamidrezabashiri.signaling.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LookUp(
    @SerializedName("code")
    @Expose
    var code: String? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("data")
    @Expose
    var data: DataLookUp? = DataLookUp()
) {
}