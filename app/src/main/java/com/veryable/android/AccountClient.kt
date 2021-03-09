package com.veryable.android

import retrofit2.Call
import retrofit2.http.GET

interface AccountClient {

    // Retrieve account info as List of AccountInfos
    @GET("/veryable.json")
    fun getAccountInfo() : Call<MutableList<AccountInfo>>
}