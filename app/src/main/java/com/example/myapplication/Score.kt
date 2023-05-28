package com.example.myapplication

class Score {
    companion object {
        var score = 0
        var max = 10
        var level = 1

        override fun toString(): String {
            return "$score/$max"
        }

        fun checkNewScore() {
            if (score >= max) {
                Money.newLevelPay(level, Timer.timeLeft)
                Money.modifyMoney(Upgrades.getTotalMoneyAfterLevel().toLong())
                CookieClicker.moneyText.text = Money.getMoneyText()
                level++;
                Timer.countDownTimer.cancel()
                Timer.countDownTimer.SetMillisInFuture(120000)
                Timer.countDownTimer.start()
                score = 0 + Upgrades.getTotalScoreAfterLevel()
                max += 10
            }
            renderScore()
        }

        fun modifyScore(value: Int) {
            score += value
            checkNewScore()
        }

        fun renderScore() {
            CookieClicker.scoreCounter.text = toString()
        }

    }

}