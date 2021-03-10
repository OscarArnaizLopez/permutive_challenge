package com.example.permutive_sdk.api

import com.example.permutive_sdk.PermutiveProvider
import com.example.permutive_sdk.PermutiveUserDataDoc
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PermutiveDocParser : JsonDeserializer<PermutiveUserDataDoc> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PermutiveUserDataDoc {

        val list = mutableListOf<PermutiveProvider>()
        val providers: JsonArray? = json?.asJsonObject?.get("providers")?.asJsonArray

        providers?.map {
            list.add(GsonProvider.simpleGsonInstance.fromJson(it, PermutiveProvider::class.java))
        }

        return PermutiveUserDataDoc(list)
    }

}
