package com.example.permutive_sdk.api

import com.example.permutive_sdk.PermutiveApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    const val BASE_URL = "http://10.0.2.2:8080/"

    fun makeRetrofitService(): PermutiveApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonProvider.fullGsonInstance))
            .build().create(PermutiveApiService::class.java)
    }
}