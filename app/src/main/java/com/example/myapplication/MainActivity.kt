package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val job = GlobalScope.launch(Dispatchers.Default) {
            repeat(5) {
                Log.d(TAG, "Coroutine is still working...")
                delay(1000L)
            }
        }

        runBlocking {
            job.join()
            Log.d(TAG, "Main Thread is continuing..")
        }
    }
}