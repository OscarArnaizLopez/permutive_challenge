package com.example.permutivedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.permutive_sdk.PermutiveSDK
import com.example.permutive_sdk.PermutiveUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MyViewModel() : ViewModel() {
    private val providers: MutableLiveData<PermutiveUserData> by lazy {
        MutableLiveData<PermutiveUserData>()
    }

    @ExperimentalCoroutinesApi
    fun loadData(userID: String) {
        viewModelScope.launch() {
            doLoadData(userID)
        }
    }

    @ExperimentalCoroutinesApi
    private suspend fun doLoadData(userID: String) {
        withContext(Dispatchers.IO) {
            val results = PermutiveSDK.getInstance().getUserData(userID)
            providers.postValue(results)
        }
    }

    fun getListProviders(): LiveData<PermutiveUserData> = providers
}

/*
// Using the ViewModel data to present the result on screen but if it is not needed you can get the list of providers like this
private fun doLoadData() {
    GlobalScope.launch {
        val permutiveProvidersList = PermutiveSDK.getInstance().getUserData("user-id")
    }
}
 */