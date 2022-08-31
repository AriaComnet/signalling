package com.hamidrezabashiri.signaling.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataLookUp(

    @SerializedName("temporary_token")
    @Expose
    var temporaryToken: String? = null

)