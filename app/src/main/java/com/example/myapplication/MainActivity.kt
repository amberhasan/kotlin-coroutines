package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            val answer = doNetworkCall()
            Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")

            //this code will now be executed in the main thread
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
                //Can update UI here with network call info, with main thread
            }
        }
    }

    private suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }
}