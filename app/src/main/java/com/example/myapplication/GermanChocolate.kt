package com.example.myapplication

import kotlin.math.pow


class GermanChocolate : Upgrade {
    companion object {
        private const val basePrice = 7
    }

    constructor() : super(basePrice, "Grandpa's German chocolate", "Doubles amount per click")

    override fun purchase() {
        super.purchase()
        this.price = basePrice.toDouble().pow(this.count.toDouble()).toInt()
    }

    override fun getClickMultiplier(): Int {
        return 2.0.pow(this.count.toDouble()).toInt()
    }
}