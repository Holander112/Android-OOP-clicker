package com.example.myapplication

import kotlin.math.pow


class GermanChocolate() :
    Upgrade(basePrice, "Grandpa's German chocolate", "Doubles amount per click") {
    companion object {
        private const val basePrice = 7
    }

    override fun purchase() {
        super.purchase()
        this.price = basePrice.toDouble().pow(this.count.toDouble()).toInt()
    }

    override fun getClickMultiplier(): Int {
        return 2.0.pow(this.count.toDouble()).toInt()
    }
}