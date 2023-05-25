package com.example.myapplication

class Futures() : Upgrade(
    basePrice,
    "Global futures investment",
    "Generates large amount per second"
) {
    companion object {
        private const val basePrice = 2000
    }

    override fun purchase() {
        super.purchase()
//        this.price = basePrice.toDouble().pow(this.count.toDouble()).toInt()
    }

    override fun getTickAmount(): Int {
        return this.count * 35
    }
}