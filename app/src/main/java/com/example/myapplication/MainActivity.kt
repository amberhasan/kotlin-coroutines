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

        //You'd still be able to use the UI in the main thread. There would be no blocking.
//        GlobalScope.launch(Dispatchers.Main) {
//            delay(10000L)
//        }

        // Only affects main thread.
        // This will block UI updates. Useful if you don't need coroutine behavior but would still like to call suspend function
        //It's the same as if we called delay outside of this block (but you can't syntactically do that bc it needs to be in coroutine)
        Log.d(TAG, "Before runBlocking")
        runBlocking {//same output as if you did Thread.sleep(5000L)
            launch (Dispatchers.IO) {//will run asynch
                delay(3000L)
                Log.d(TAG, "Finished IO Coroutine 1")
            }
            launch (Dispatchers.IO) {//will run asynch
                delay(3000L)
                Log.d(TAG, "Finished IO Coroutine 2")
            }
            Log.d(TAG, "Start of runBlocking")
            delay(5000L)
            Log.d(TAG, "End of runBlocking")
        }
        Log.d(TAG, "After runBlocking")
    }
}