package com.example.myapplication

import android.graphics.Rect
import android.view.MotionEvent
import android.widget.ImageButton

class CookieClicked {
    companion object {

        //When screen is clicked calculate if score need to be incremented or direct to shop
        fun screenTouched(event: MotionEvent) {
            val shopButton: ImageButton = CookieClicker.shopButton

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
            Score.checkNewScore()
        }
    }

}