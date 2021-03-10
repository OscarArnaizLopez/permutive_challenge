package com.example.permutive_sdk

import org.junit.Assert
import org.junit.Test

class PermutiveSDKTest {

    @Test
    fun `on getIntance being multi-instanced THEN it returns always the same-single instance`() {
        val firstIntance = PermutiveSDK.getInstance()
        val expectedSameInstance = PermutiveSDK.getInstance()

        Assert.assertSame(expectedSameInstance, firstIntance)
    }
}