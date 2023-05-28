package com.example.myapplication

import android.widget.TextView
import kotlin.random.Random

// Money static object class
class Money {
    companion object {
        private var money: Long = 0
        private var prevMoney: Long = 0

        fun getMoney(): Long {
            return money
        }

        //Modify money with negative or positive modifier
        fun modifyMoney(modifier: Long) {
            money += modifier;
            renderMoney()
        }

        fun getMoneyText(): String {
            return getMoney().toString() + "$"
        }

        //On level completion change money based on level, time left and random modifier
        fun newLevelPay(level: Int, timeLeft: Long) {
            var bonus = level * level * (timeLeft / 1000) * (Random.nextInt(5, 10) / 2)
            modifyMoney(bonus)
            prevMoney = money
            renderMoney()
        }

        //On level fail reset money to previous
        fun resetMoney() {
            money = prevMoney
            renderMoney()
        }

        fun renderMoney() {
            CookieClicker.moneyText.text = Money.getMoney().toString() + "$"
        }
    }
}