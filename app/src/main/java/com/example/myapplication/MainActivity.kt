package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //suspend functions are synchronous by default.
        //this will delay 3 seconds each and will actually delay 6 seconds.
        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                var answer1: String? = null
                var answer2: String? = null
                val job1 = launch { answer1 = networkCall1() }
                val job2 = launch { answer2 = networkCall2() }
                job1.join()
                job2.join()
                Log.d(TAG, "Answer1 is $answer1")
                Log.d(TAG, "Answer2 is $answer2")
            }
            Log.d(TAG, "Requests took $time ms.")
        }
    }

    suspend fun networkCall1(): String {
        delay(3000L)
        return "Answer 1"
    }

    suspend fun networkCall2(): String {
        delay(3000L)
        return "Answer 2"
    }

}