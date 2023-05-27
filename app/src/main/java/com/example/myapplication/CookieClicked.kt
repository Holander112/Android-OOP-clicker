package com.example.myapplication

import android.graphics.Rect
import android.view.MotionEvent
import android.widget.ImageButton

class CookieClicked {
    companion object{

        //When screen is clicked calculate if score need to be incremented or direct to shop
        fun screenTouched(event:MotionEvent){
            val shopButton:ImageButton = CookieClicker.shopButton

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

        }

        //If screen is clicked that is not a button
        private fun cookieClicked() {
            //Increment the score
            Score.score += (1 + Upgrades.getTotalScoreModifier()) * Upgrades.getTotalScoreMultiplier()
            //Set new max if previous is reached
            if (Score.score >= Score.max) {
                Money.newLevelPay(Score.level, Timer.timeLeft)
                Money.modifyMoney(Upgrades.getTotalMoneyAfterLevel().toLong())
                CookieClicker.moneyText.text = Money.getMoneyText()
                Score.level++;
                Timer.countDownTimer.cancel()
                Timer.countDownTimer.SetMillisInFuture(120000)
                Timer.countDownTimer.start()
                Score.score = 0 + Upgrades.getTotalScoreAfterLevel()
                Score.max += 10
            }
            //Print them on UI
            CookieClicker.scoreCounter.text = Score.getScoreText()
        }
    }

}