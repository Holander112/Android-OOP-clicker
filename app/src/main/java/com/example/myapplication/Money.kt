package com.example.myapplication

import kotlin.random.Random
// Money static object class
class Money {
    companion object {
        private var money: Long = 0
        private var prevMoney: Long = 0

        fun GetMoney(): Long{
            return money
        }
        //Modify money with negative or positive modifier
        fun ModifyMoney(modifier: Long){
            money = money + modifier;
        }
        //On level completion change money based on level, time left and random modifier
       fun NewLevelPay(level: Int, timeLeft: Long){
           prevMoney = money
           var bonus = level*level * (timeLeft/1000) * (Random.nextInt(5,10)/2)
           ModifyMoney(bonus)
       }
        //On level fail reset money to previous
        fun ResetMoney(){
            money = prevMoney
        }
    }
}