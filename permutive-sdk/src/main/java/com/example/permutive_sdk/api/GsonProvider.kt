package com.example.permutive_sdk.api

import com.example.permutive_sdk.PermutiveUserDataDoc
import java.lang.reflect.Modifier
import com.google.gson.Gson
import com.google.gson.GsonBuilder


class GsonProvider {

    companion object {

        // Optional milliseconds for JWTs - NB: SimpleDateFormat doesn't accept optionals
        const val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss[.SSS]'Z'"

        @JvmStatic
        val simpleGsonInstance: Gson by lazy {
            commonGsonBuilder()
                .create()
        }

        @JvmStatic
        val fullGsonInstance: Gson by lazy {
            commonGsonBuilder()
                .registerTypeAdapter(
                    PermutiveUserDataDoc::class.java,
                    PermutiveDocParser()
                )
                .create()
        }

        private fun commonGsonBuilder(): GsonBuilder = GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.ABSTRACT, Modifier.STATIC, Modifier.TRANSIENT)
            .setDateFormat(DATE_FORMAT)
    }

}