package com.example.myapplication

import kotlin.math.pow


class GermanChocolate() :
    Upgrade(basePrice, "Grandpa' German chocolate", "2x per click", R.drawable.choopss) {
    companion object {
        private const val basePrice = 7
    }

    override fun purchase() {
        super.purchase()
        this.price = basePrice.toDouble().pow(this.count.toDouble()).toInt()
    }

    override fun getScoreMultiplier(): Int {
        return 2.0.pow(this.count.toDouble()).toInt()
    }
}