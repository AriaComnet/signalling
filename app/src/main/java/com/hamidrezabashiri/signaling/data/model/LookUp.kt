package com.hamidrezabashiri.signaling.data.model

import com.google.gson.annotations.SerializedName

data class LookUp(
    @SerializedName("code") var code: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: DataLookUp? = DataLookUp()
) {
}