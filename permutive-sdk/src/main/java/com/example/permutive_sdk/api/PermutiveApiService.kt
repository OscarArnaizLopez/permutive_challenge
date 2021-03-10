package com.example.permutive_sdk

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

data class PermutiveUserData (val userID: String, var providers: List<PermutiveProvider>?, var successfulCall: Boolean, var resultFromCache:Boolean, val dateResult: Long )
data class PermutiveUserDataDoc (val providers: List<PermutiveProvider>?)
data class PermutiveProvider(val id: String?, val segments: MutableList<String>? )

interface PermutiveApiService {

    @GET("/api/v1/data")
    suspend fun getListProviders(@Query("user_id") userID: String): Response<PermutiveUserDataDoc>

}