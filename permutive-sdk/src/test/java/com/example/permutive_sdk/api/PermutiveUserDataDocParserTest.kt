package com.example.permutive_sdk.api

import com.example.permutive_sdk.PermutiveProvider
import com.example.permutive_sdk.PermutiveUserDataDoc
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test

class PermutiveUserDataDocParserTest {

    @Test
    fun `on successfully parsing THEN get a PermutiveUserDataDoc`() {
        val json = """{
                         "providers": [
                             {
                                "id": "12345",
                                "segments": ["male", "gamer"]
                             },
                             {
                                "id": "6789",
                                "segments": ["female", "technology"]
                             }
                        ]
                    }"""

        val gson = GsonBuilder()
            .registerTypeAdapter(PermutiveUserDataDoc::class.java, PermutiveDocParser())
            .setDateFormat(GsonProvider.DATE_FORMAT)
            .create()

        val expected = PermutiveUserDataDoc(
            listOf(
                PermutiveProvider("12345", mutableListOf("male", "gamer")),
                PermutiveProvider("6789", mutableListOf("female", "technology"))
            )
        )
        val parserResponse = gson.fromJson(json, PermutiveUserDataDoc::class.java)

        Assert.assertEquals(expected, parserResponse)
    }

}