package com.example.permutive_sdk

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.*
import java.util.*


class SDKManager(private val service: PermutiveApiService) : ISDKManager {

    private var listProviders: PermutiveUserData? = null

    @ExperimentalCoroutinesApi
    override suspend fun getUserData(userID: String): PermutiveUserData? =
        withContext(Dispatchers.IO) {

            return@withContext if (listProviders != null && isTimeWithinBounds(listProviders?.dateResult)) {

                listProviders?.resultFromCache = true
                listProviders

            } else suspendCancellableCoroutine {

                GlobalScope.launch {
                    try {
                        val response = service.getListProviders(userID)
                        listProviders = PermutiveUserData(
                            userID = userID,
                            providers = response.body()?.providers ?: emptyList(),
                            successfulCall = response.isSuccessful,
                            resultFromCache = false,
                            dateResult = System.currentTimeMillis()
                        )

                        it.resume(listProviders) { Throwable("Permutive data request was cancelled") }
                    } catch (e: Exception) {
                        println("Something went wrong when trying to get Permutive data. Error => " + e.message)
                    }
                }
            }
        }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun isTimeWithinBounds(time: Long?): Boolean {
        return time != null && Date(time).after(Date(System.currentTimeMillis() - (1 /*<==  set to 1 minute for testing purposes, replace it with 20 to achieve the challenge requirement  */ * 60 * 1000))) // 1 mins past now
    }
}