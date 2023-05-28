package com.example.myapplication

import android.widget.TextView

class Timer {
    companion object {
        var timeLeft: Long = 120000
        lateinit var clickTimer: TextView

        val countDownTimer = object : CountDownTimer(timeLeft, 1000) {

            //Override the tick method to make a 2 minute timer
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                //Get minutes and seconds
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                //Format the values in mm:ss
                val displaySeconds = String.format("%02d", remainingSeconds)
                val displayMinutes = String.format("%02d", minutes)
                //Print the result in the UI
                clickTimer.text = displayMinutes + ':' + displaySeconds

                // process tick events from upgrades
                Money.modifyMoney(Upgrades.getTotalMoneyPerTick().toLong())
                Score.modifyScore(Upgrades.getTotalScorePerTick())
            }

            override fun onFinish() {
                //Level reset
                Score.score = 0
                CookieClicker.scoreCounter.text =
                    Score.score.toString() + "/" + Score.max.toString()
                Money.resetMoney()
                Money.renderMoney()
                this.cancel()
                this.SetMillisInFuture(120000)
                this.start()
            }

        }


    }

}