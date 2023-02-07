package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val networkCallAnswer = doNetworkCall()
            val networkCallAnswer2 = doNetworkCall2()
            Log.d(TAG, networkCallAnswer)
            Log.d(TAG, networkCallAnswer2)
        }
    }

    //These both execute at the same time because they are called in the same coroutine.
    //They both delay 3 seconds and then print at the same time.
    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }
    suspend fun doNetworkCall2(): String {
        delay(3000L)
        return "This is the answer"
    }
}