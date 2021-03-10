package com.example.permutive_sdk

internal interface ISDKManager {

    suspend fun getUserData(userID: String): PermutiveUserData?

}