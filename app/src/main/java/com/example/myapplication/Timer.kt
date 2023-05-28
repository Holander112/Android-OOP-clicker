package com.example.myapplication

import android.widget.TextView

class Timer {
    companion object {
        val duration: Long = 120000
        var timeLeft: Long = duration
        lateinit var clickTimer: TextView
        var lastUpdatedTick: Long = 0

        val countDownTimer = object : CountDownTimer(timeLeft, 1000) {

            //Override the tick method to make a 2 minute timer
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                //Get minutes and seconds
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60

                if (seconds != lastUpdatedTick) {
                    //Format the values in mm:ss
                    val displaySeconds = String.format("%02d", remainingSeconds)
                    val displayMinutes = String.format("%02d", minutes)
                    //Print the result in the UI
                    clickTimer.text = "$displayMinutes:$displaySeconds"
                    // process tick events from upgrades
                    Money.modifyMoney(Upgrades.getTotalMoneyPerTick().toLong())
                    Score.modifyScore(
                        Upgrades.getTotalScorePerTick()
                    )
                    lastUpdatedTick = seconds
                }
            }

            override fun onFinish() {
                //Level reset
                Score.score = 0
                Score.renderScore()
                Money.resetMoney()
                Money.renderMoney()
                Upgrades.resetUpgrades()
                this.cancel()
                this.SetMillisInFuture(duration)
                this.start()
            }

        }


    }

}