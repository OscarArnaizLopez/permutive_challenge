package com.example.permutivedemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.permutive_sdk.PermutiveSDK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProviders
import com.example.permutive_sdk.PermutiveProvider
import com.example.permutive_sdk.PermutiveUserData
import java.util.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.get_data_button)
        val userIDText: TextView = findViewById(R.id.user_id)
        val resultCached: TextView = findViewById(R.id.result_cached)
        val segmentText: TextView = findViewById(R.id.segments)
        val dateResultText: TextView = findViewById(R.id.result_date)

        val permutiveDataModel = ViewModelProviders.of(this)[MyViewModel::class.java]
        permutiveDataModel.getListProviders().observe(this, { permutiveData ->

            userIDText.text = "UserID => " + permutiveData.userID
            resultCached.text = "Results from cache => " + permutiveData.resultFromCache.toString()
            dateResultText.text = "Date results =>" + Date(permutiveData.dateResult)
            var segments = ""


            permutiveData.providers?.forEach {
                val providerID = it.id
                val segmentData = it.segments
                segments += """
                            | 
                            |------------------------------------------------------
                            |
                            |Provider: $providerID
                            |
                            |------------------------------------------------------
                            |${segmentData?.joinToString("\n|")}
                            |
                            """.trimMargin()
                segmentText.text = segments
            }
        })

        permutiveDataModel.loadData("user-id")

        button.setOnClickListener {
            userIDText.text = ""
            resultCached.text = ""
            segmentText.text = ""
            permutiveDataModel.loadData("user-id")
        }
    }
}

