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
            Log.d(TAG, "Starting long running calculation....")
            for(i in 30..40) {
                if(isActive){ //must check if the job has been canceled; otherwise this job will be so busy and not notice
                    Log.d(TAG, "Result for i = $i: ${fib(i)}")
                }
            }
            Log.d(TAG, "Ending long running calculation...")
        }

        runBlocking {
            delay(2000L)
            job.cancel()
            Log.d(TAG, "Canceled job...")
        }
    }

    fun fib(n: Int): Long {
        return if(n==0) 0
        else if(n==1) 1
        else fib(n-1) + fib(n-2)
    }
}