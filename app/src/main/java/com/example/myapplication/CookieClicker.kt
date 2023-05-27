package com.example.myapplication

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.TextView

class CookieClicker : MainActivity() {
    companion object{
        lateinit var scoreCounter: TextView
        lateinit var shopButton: ImageButton
       lateinit var moneyText: TextView
       lateinit var clickTimer: TextView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Initialization
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Get elements from UI
        scoreCounter = findViewById(R.id.clickCounter)
        shopButton = findViewById(R.id.shopButton)
        moneyText = findViewById(R.id.moneyText)
        clickTimer = findViewById(R.id.Clicktimer)
        //Listener to go to shop and send seconds left on timer
        shopButton.setOnClickListener() {
            val intent = Intent(this, ShopActivity::class.java)
            intent.putExtra("timeLeft", Timer.timeLeft)
            startActivity(intent)
        }
        //Print current values
        scoreCounter.text = Score.getScoreText()
        moneyText.text = Money.getMoneyText()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        //When user resumes using this screen timer gets reset
        Timer.clickTimer = clickTimer
        Timer.countDownTimer.SetMillisInFuture(Timer.timeLeft)
        Timer.countDownTimer.start()
    }

    override fun onPause() {
        //When user exits this screen cancel the timer and set its time to time that was left
        Timer.countDownTimer.cancel()
        Timer.countDownTimer.SetMillisInFuture(Timer.timeLeft)
        super.onPause()

    }

    //When screen is clicked calculate if score need to be incremented or direct to shop
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        CookieClicked.screenTouched(event)
        return super.dispatchTouchEvent(event)
    }





}