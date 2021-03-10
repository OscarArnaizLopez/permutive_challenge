package com.example.permutive_sdk

import com.example.permutive_sdk.api.RetrofitFactory

object PermutiveSDK {

    private var manager: SDKManager? = null

    fun getInstance(): SDKManager {
        if (manager == null) {
            manager = SDKManager(RetrofitFactory.makeRetrofitService())
        }
        return manager as SDKManager
    }

}