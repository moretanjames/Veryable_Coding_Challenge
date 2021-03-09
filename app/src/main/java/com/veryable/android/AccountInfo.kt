package com.veryable.android

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// data class to deserialize json data into an object.
data class AccountInfo(
    @SerializedName("id")
    val accountId: Int,
    @SerializedName("account_type")
    val accountType: String,
    @SerializedName("account_name")
    val accountName: String,
    @SerializedName("desc")
    val description: String
) : Serializable