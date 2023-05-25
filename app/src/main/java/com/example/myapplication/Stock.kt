package com.example.myapplication

class Stock() : Upgrade(basePrice, "Local stock investment", "Generates small amount per second") {
    companion object {
        private const val basePrice = 200
    }

    override fun purchase() {
        super.purchase()
//        this.price = basePrice.toDouble().pow(this.count.toDouble()).toInt()
    }

    override fun getTickAmount(): Int {
        return this.count
    }
}