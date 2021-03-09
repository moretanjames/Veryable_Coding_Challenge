package com.veryable.android

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class AccountInfoViewModel(application: Application) : AndroidViewModel(application) {

    val accountInfoLiveData =
        MutableLiveData<MutableList<AccountInfo>>() // live data observed in PayoutsListActivity

    fun getAccountInfo() = viewModelScope.launch(Dispatchers.IO) {
        var response: Response<MutableList<AccountInfo>>? = null
        try {
            response =
                RetrofitClientInstance().getRetrofitInstance()?.create(AccountClient::class.java)
                    ?.getAccountInfo()?.execute()
        } catch (e: Exception) {
            Log.d(LOG_TAG, e.toString())
        }

        if (response?.isSuccessful == true) {
            accountInfoLiveData.postValue(response.body())
        } else {
            accountInfoLiveData.postValue(mutableListOf())
        }
    }

    companion object {
        const val LOG_TAG = "AccountInfoViewModel"
    }
}