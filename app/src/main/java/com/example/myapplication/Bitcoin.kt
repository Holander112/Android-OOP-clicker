package com.example.myapplication

import kotlin.math.pow

class Bitcoin : Upgrade {
    companion object {
        private const val basePrice = 50
    }

    constructor() : super(basePrice, "Invest in bitcoin", "Random amount after completing level")

    override fun purchase() {
        super.purchase()
        this.price = basePrice.toDouble().pow(this.count.toDouble()).toInt()
    }

    override fun getAfterLevelAmount(): Int {
        return basePrice * 2.0.pow(Math.random() * 4 - 2).toInt()
    }
}