package com.example.permutive_sdk

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import retrofit2.Response

internal class SDKManagerTest {

    private lateinit var APIservice: PermutiveApiService
    private val userID = "user-id"
    private val expectedList = PermutiveUserDataDoc(
        listOf(
            PermutiveProvider("12345", mutableListOf("male", "gamer")),
            PermutiveProvider("6789", mutableListOf("female", "technology"))
        )
    )

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        APIservice = mock()
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `on requesting data THEN counting results from mocked reply`() = runBlockingTest {
        whenever(APIservice.getListProviders(userID)).thenReturn(Response.success(expectedList))

        val manager = SDKManager(APIservice)
        val listProvider = manager.getUserData(userID)

        Assert.assertEquals(2, listProvider?.providers)

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `on requesting data WHEN result is not cached THEN confirm resultFromCache indicator reflects it`() =
        runBlockingTest {
            whenever(APIservice.getListProviders(userID)).thenReturn(Response.success(expectedList))

            val manager = SDKManager(APIservice)
            val listProvider = manager.getUserData(userID)

            Assert.assertEquals(false, listProvider?.resultFromCache)

        }

    @ExperimentalCoroutinesApi
    @Test
    fun `on requesting data WHEN result is cached THEN confirm resultFromCache indicator reflects it`() =
        runBlockingTest {
            whenever(APIservice.getListProviders(userID)).thenReturn(Response.success(expectedList))
            whenever(SDKManager(APIservice).isTimeWithinBounds(anyLong())).thenReturn(false)

            val manager = SDKManager(APIservice)
            val listProvider = manager.getUserData(userID)

            Assert.assertEquals(true, listProvider?.resultFromCache)

        }
}