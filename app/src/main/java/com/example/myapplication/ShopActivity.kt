package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ShopActivity:MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //initialization
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop)
    }

    override fun onStart() {
        super.onStart()
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener() {
            onPause()
        }
        // Get passed time data using intent object
        val intent = intent
        val time = intent.getLongExtra("timeLeft", 100000)

        val seconds = time / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        // format the values in mm:ss
        val displaySeconds = String.format("%02d", remainingSeconds)
        val displayMinutes = String.format("%02d", minutes)
        // print the result in the UI
        val timeLeft : TextView = findViewById(R.id.timeLeft)
        timeLeft.text = displayMinutes + ':' + displaySeconds
    }
    override fun onResume() {
        super.onResume()
        }

        override fun onPause() {
            super.onPause()
            finish()
        }
}