package com.example.myapplication

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import com.example.myapplication.CountDownTimer
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.TextView
import org.w3c.dom.Text

class CookieClicker : MainActivity() {
    private lateinit var scoreCounter: TextView
    private lateinit var shopButton: ImageButton
    private lateinit var moneyText: TextView
    private var timeLeft: Long = 120000
    private var score = 0
    private var max = 10
    private var level = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        //Initialization
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Get elements from UI
        scoreCounter = findViewById(R.id.clickCounter)
        shopButton = findViewById(R.id.shopButton)
        moneyText = findViewById(R.id.moneyText)

        //Listener to go to shop and send seconds left on timer
        shopButton.setOnClickListener() {
            val intent = Intent(this, ShopActivity::class.java)
            intent.putExtra("timeLeft", timeLeft)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        //When user resumes using this screen timer gets reset
        countDownTimer.SetMillisInFuture(timeLeft)
        countDownTimer.start()
    }

    override fun onPause() {
        //When user exits this screen cancel the timer and set its time to time that was left
        countDownTimer.cancel()
        countDownTimer.SetMillisInFuture(timeLeft)
        super.onPause()

    }

    //When screen is clicked calculate if score need to be incremented or direct to shop
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val shopButton = findViewById<ImageButton>(R.id.shopButton)

        // Check if the touch event is within the button's bounds
        val x = event.rawX.toInt()
        val y = event.rawY.toInt()
        val buttonRect = Rect()
        shopButton.getGlobalVisibleRect(buttonRect)
        if (!buttonRect.contains(x, y)) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    cookieClicked()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    //When screen gets rotated OnCreate gets called, so data needs to be saved
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("score", score)
        outState.putInt("max", max)
        outState.putInt("level", level)
        outState.putLong("timeLeft", timeLeft)

    }

    //Retrieve data and text after screen rotation
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore necessary data from the bundle
        score = savedInstanceState.getInt("score")
        max = savedInstanceState.getInt("max")
        level = savedInstanceState.getInt("level")
        timeLeft = savedInstanceState.getLong("timeLeft")
        scoreCounter.text = score.toString() + "/" + max.toString()
        moneyText.text = Money.GetMoney().toString() + "$"
    }

    //If screen is clicked that is not a button
    private fun cookieClicked() {
        //Increment the score
        score += (1 + Upgrades.getTotalClickerModifier()) * Upgrades.getTotalClickerMultiplier()
        //Set new max if previous is reached
        if (score >= max) {
            Money.NewLevelPay(level, timeLeft)
            moneyText.text = Money.GetMoney().toString() + "$"
            level++
            countDownTimer.cancel()
            countDownTimer.SetMillisInFuture(120000)
            countDownTimer.start()
            score = 0 + Upgrades.getTotalAfterLevelAmount()
            max = max + 10
        }
        //Print them on UI
        scoreCounter.text = score.toString() + "/" + max.toString()
    }


    val countDownTimer = object : CountDownTimer(timeLeft, 1000) {

        //Override the tick method to make a 2 minute timer
        override fun onTick(millisUntilFinished: Long) {
            //Get minutes and seconds
            timeLeft = millisUntilFinished
            val seconds = millisUntilFinished / 1000
            val minutes = seconds / 60
            val remainingSeconds = seconds % 60
            //Format the values in mm:ss
            val displaySeconds = String.format("%02d", remainingSeconds)
            val displayMinutes = String.format("%02d", minutes)
            //Print the result in the UI
            val Clicktimer: TextView = findViewById(R.id.Clicktimer)
            Clicktimer.text = displayMinutes + ':' + displaySeconds
        }

        override fun onFinish() {
            //Level reset
            score = 0
            scoreCounter.text = score.toString() + "/" + max.toString()
            Money.ResetMoney()
            moneyText.text = Money.GetMoney().toString() + "$"
            this.cancel()
            this.SetMillisInFuture(120000)
            this.start()
        }
    }

}