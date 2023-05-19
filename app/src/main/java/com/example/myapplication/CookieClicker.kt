package com.example.myapplication

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import com.example.myapplication.CountDownTimer
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.TextView

class CookieClicker:MainActivity() {
    private lateinit var scoreCounter: TextView
    private lateinit var shopButton: ImageButton
    private var timeLeft:Long = 120000;
    private var  score = 0;
    private var max = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        //initialization
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get elements from UI
        scoreCounter = findViewById(R.id.clickCounter);
        shopButton = findViewById(R.id.shopButton);

        //Listener to go to shop and send seconds left on timer
        shopButton.setOnClickListener() {
            val  intent = Intent(this,ShopActivity::class.java)
            intent.putExtra("timeLeft", timeLeft)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        countDownTimer.start()
    }

    override fun onPause() {
        //When user exits this screen cancel the timer and set its time to time that was left
        countDownTimer.cancel()
        countDownTimer.SetMillisInFuture(timeLeft)
        super.onPause()

    }

   // When screen is clicked calculate if score need to be incremented or direct to shop
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val shopButton = findViewById<ImageButton>(R.id.shopButton)

        // Check if the touch event is within the button's bounds
        val x = event.rawX.toInt()
        val y = event.rawY.toInt()
        val buttonRect = Rect()
        shopButton.getGlobalVisibleRect(buttonRect)

        if (!buttonRect.contains(x, y)){
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    cookieClicked()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }


    // If screen is clicked that is not a button
    private fun cookieClicked() {
        // Increment the score
        score++
        // Set new max if previous is reached
        if (score == max) {
            countDownTimer.start()
            score = 0
            max = max + 10
        }
        // Print them on UI
        scoreCounter.text = score.toString() + "/" + max.toString()
    }


    val countDownTimer = object : CountDownTimer(timeLeft, 1000) {

        // Override the tick method to make a 2 minute timer
        override fun onTick(millisUntilFinished: Long) {
            //get minutes and seconds
            timeLeft = millisUntilFinished
            var seconds = millisUntilFinished / 1000
            var minutes = seconds / 60
            val remainingSeconds = seconds % 60
            // format the values in mm:ss
            val displaySeconds = String.format("%02d", remainingSeconds)
            val displayMinutes = String.format("%02d", minutes)
            // print the result in the UI
            val Clicktimer : TextView = findViewById(R.id.Clicktimer)
            Clicktimer.text = displayMinutes + ':' + displaySeconds
        }

        override fun onFinish() {
            //game over
        }
    }

}