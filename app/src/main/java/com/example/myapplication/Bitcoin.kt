package com.example.myapplication

import kotlin.math.pow

class Bitcoin() : Upgrade(basePrice, "Invest in bitcoin", "Random amount after\ncompleting level", R.drawable.bitstonkver) {
    companion object {
        private const val basePrice = 50
    }

    override fun purchase() {
        super.purchase()
        this.price = basePrice.toDouble().pow(this.count.toDouble()).toInt()
    }

    override fun getMoneyAfterLevel(): Int {
        return basePrice * 2.0.pow(Math.random() * 4 - 2).toInt()
    }
}